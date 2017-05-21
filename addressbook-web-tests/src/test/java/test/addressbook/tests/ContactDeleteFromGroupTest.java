package test.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().createC(new ContactData()
                    .withName("Masha").withLastname("Ivanova").withMobilenumber("79773456757"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().createG(new GroupData().withName("test1"));
            app.goTo().homePage();
        }
        if (app.db().groupsWithContact().size() == 0) {
            Contacts contacts = app.db().contacts();
            Groups groups = app.db().groups();
            app.contact().addToGroup(contacts.iterator().next(), groups.iterator().next());
            app.goTo().homePage();
        }
    }

    @Test
    public void testDeleteContactFromGroup() throws InterruptedException {
        Groups list = app.db().groups();
        ContactsInGroupList before = app.db().groupsWithContact();
        ContactInGroupData toDelete = before.iterator().next();
        app.contact().removeFromGroup(list, toDelete);
        ContactsInGroupList after = app.db().groupsWithContact();

        System.out.println("before: " + before.without(toDelete));
        System.out.println("after: " + after);

        assertThat(after, equalTo(before.without(toDelete)));
    }
}
