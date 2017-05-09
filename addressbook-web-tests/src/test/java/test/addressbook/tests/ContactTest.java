package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTest extends TestBase {

    @Test
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
