package test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import test.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {


    public ContactHelper(WebDriver wd) {
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

    public void selectContact() {
        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void acceptAlertPopUp() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void createContact(ContactData contact) {
        addNewContact();
        enterData(contact);
        submitContact();
        returnHome();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }
}
