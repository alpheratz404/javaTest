package test.addressbook;

import org.testng.annotations.Test;

public class GroupTests extends TestBase {

    @Test
    public void groupTest() {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "test2", "test3"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
