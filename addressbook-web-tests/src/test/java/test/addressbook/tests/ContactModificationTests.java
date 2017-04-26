package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Masha", "Ivanova", "79773456757", null, null));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().enterData(new ContactData("Masha", "Ivanova", "79773456757", "gav@gav.ru", "city"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnHome();
    }
}
