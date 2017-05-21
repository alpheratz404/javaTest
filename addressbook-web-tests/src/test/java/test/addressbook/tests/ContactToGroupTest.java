package test.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;
import test.addressbook.model.ContactInGroupData;
import test.addressbook.model.ContactsInGroupList;
import test.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupTest extends TestBase {

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
    }

    @Test
    public void testAddContactToGroupTests() {
        ContactsInGroupList before = app.db().groupsWithContact();
        ContactData contactToAddToGroup = app.db().contacts().iterator().next();
        GroupData groupToAddContactTo = app.db().groups().iterator().next();
        app.contact().addToGroup(contactToAddToGroup, groupToAddContactTo);
        ContactsInGroupList after = app.db().groupsWithContact();

        assertThat(after, equalTo(before.withAdded(new ContactInGroupData()
                .withGroupId(groupToAddContactTo.getId()))));
    }
}