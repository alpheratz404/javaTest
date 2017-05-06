package test.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().listC().size() == 0) {
            app.contact().createC(new ContactData().withName("Masha")
                    .withLastname("Ivanova").withMobilenumber("79773456757"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().allC();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withName("Masha").withLastname("Ivanova")
                .withMobilenumber("79773456757").withEmail("gav@gav.ru").withCityname("city");
        app.contact().modifyC(contact);
        Contacts after = app.contact().allC();

        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
