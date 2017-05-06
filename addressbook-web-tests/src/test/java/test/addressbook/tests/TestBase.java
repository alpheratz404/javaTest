package test.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeSuite;
import test.addressbook.appmanager.ApplicationManager;


public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
