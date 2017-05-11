package test.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTest extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContacts")
    public void testContact(ContactData contact) {
        Contacts before = app.contact().allC();
        app.contact().createC(contact);
        assertThat(app.contact().countC(), equalTo(before.size() + 1));
        Contacts after = app.contact().allC();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void testContact() {
        app.goTo().homePage();
        Contacts before = app.contact().allC();
        ContactData contact = new ContactData().withName("Masha").withLastname("Ivanova")
                .withHomePhone("111").withMobilenumber("79773456757").withWorkPhone("333")
                .withCityname("neverland").withEmail("gav@gav.ru").withEmail2("gav2@gav.ru")
                .withEmail3("gav3@gav.ru");
        app.contact().createC(contact);
        assertThat(app.contact().countC(), equalTo(before.size() + 1));
        Contacts after = app.contact().allC();

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) ->
                        c.getId()).max().getAsInt()))));
    }

}
