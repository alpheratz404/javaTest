package test.addressbook;

import org.testng.annotations.Test;

public class ContactTest extends TestBase {

    @Test
    public void testContact() {
        addNewContact();
        enterData(new ContactData("Masha", "Ivanova", "79773456757", "gav@gav.ru", "city"));
        submitContact();
        returnHome();
    }
}
