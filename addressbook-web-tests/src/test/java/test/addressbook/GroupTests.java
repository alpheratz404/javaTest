package test.addressbook;

import org.testng.annotations.Test;

public class GroupTests extends TestBase {

    @Test
    public void groupTest() {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
