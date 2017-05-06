package test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.addressbook.model.ContactData;
import test.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectContact(int index) {
        wd.findElements(By.xpath(".//td/input")).get(index).click();
    }
    public void selectContactById (int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void acceptAlertPopUp() {
        wd.switchTo().alert().accept();
    }

    public void initContactModification(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void createC(ContactData contact) {
        addNewContact();
        enterData(contact);
        submitContact();
        returnHome();
    }

    public void modifyC(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        enterData(contact);
        submitContactModification();
        returnHome();
    }

    public void deleteC(int index) {
        selectContact(index);
        deleteSelectedContacts();
        acceptAlertPopUp();
        returnHome();
    }
    public void deleteC(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        acceptAlertPopUp();
        returnHome();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

    public int getContactCount() {
        return wd.findElements(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input")).size();
    }

    public List<ContactData> listC() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : elements){
            String name =  element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname));
        }
        return contacts;
    }
        public Contacts allC() {
            Contacts contacts = new Contacts();
            List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
            for (WebElement element : elements) {
                String name = element.findElement(By.xpath(".//td[3]")).getText();
                String lastname = element.findElement(By.xpath(".//td[2]")).getText();
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname));
            }
            return contacts;
        }
}
