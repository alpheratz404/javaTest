package test.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().listC().size() == 0) {
            app.contact().createC(new ContactData()
                    .withName("Masha").withLastname("Ivanova").withMobilenumber("79773456757"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.contact().allC();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteC(deletedContact);
        Contacts after = app.contact().allC();

        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
