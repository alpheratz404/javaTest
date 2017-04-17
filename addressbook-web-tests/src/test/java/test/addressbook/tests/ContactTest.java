package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

public class ContactTest extends TestBase {

    @Test
    public void testContact() {
        app.getContactHelper().addNewContact();
        app.getContactHelper().enterData(new ContactData("Masha", "Ivanova", "79773456757", null, null));
        app.getContactHelper().submitContact();
        app.getContactHelper().returnHome();
    }
}
