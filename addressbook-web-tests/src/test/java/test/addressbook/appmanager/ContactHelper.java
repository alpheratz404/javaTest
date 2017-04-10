package test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import test.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {


    public ContactHelper(FirefoxDriver wd) {
        super(wd); }

    public void returnHome() {
        click(By.linkText("home"));
    }

    public void submitContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void enterData(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobilenumber());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address2"), contactData.getCityname());
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }
}
