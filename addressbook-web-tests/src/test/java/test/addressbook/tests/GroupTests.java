package test.addressbook.tests;

import org.testng.annotations.Test;
import test.addressbook.model.GroupData;

public class GroupTests extends TestBase {

    @Test
    public void groupTest() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }

}


