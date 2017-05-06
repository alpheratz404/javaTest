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
        ContactData contact = new ContactData().withName("Masha").withLastname("Ivanova").withMobilenumber("79773456757");
        app.contact().createC(contact);
        Contacts after = app.contact().allC();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) ->
                        c.getId()).max().getAsInt()))));
    }
}
