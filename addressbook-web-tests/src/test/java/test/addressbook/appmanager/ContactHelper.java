package test.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.addressbook.model.*;

import java.util.ArrayList;
import java.util.List;


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
        contactCache = null;
        returnHome();
    }

    public void modifyC(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification(contact.getId());
        enterData(contact);
        submitContactModification();
        contactCache = null;
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
        contactCache = null;
        returnHome();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
    }

    public int countC() {
        return wd.findElements(By.name("selected[]")).size();
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

    private Contacts contactCache = null;

        public Contacts allC() {
            if (contactCache !=null) {
                return  new Contacts(contactCache);
        }
            contactCache = new Contacts();
            List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
            for (WebElement element : elements) {
                List<WebElement> cells = element.findElements(By.tagName("td"));
                String lastname = cells.get(1).getText();
                String name = cells.get(2).getText();
                String address = cells.get(3).getText();
                String allEmails = cells.get(4).getText();
                String allPhones = cells.get(5).getText();


                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                contactCache.add(new ContactData().withId(id).withName(name)
                        .withLastname(lastname).withCityname(address).withAllEmails(allEmails)
                        .withAllPhones(allPhones));
            }
            return new Contacts(contactCache);
        }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobilenumber = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstName).withLastname(lastName)
                .withHomePhone(home).withMobilenumber(mobilenumber).withWorkPhone(work)
                .withCityname(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public void addToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        submitAddContactToGroup(group);
    }
    private void submitAddContactToGroup(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
        click(By.name("add"));
    }

    public void removeFromGroup(Groups list, ContactInGroupData toDelete) {
        selectGroup(list, toDelete);
        selectContact();
        submitDeleteContactFromGroup();
    }

        private void selectContact() {
            click(By.name("selected[]"));
    }

    private void submitDeleteContactFromGroup() {
        click(By.name("remove"));
    }

    private void selectGroup(Groups list, ContactInGroupData toDelete) {
        String groupToSelect = null;

        for (GroupData s : list) {
            if (s.getId() == toDelete.getGroupId()) {
                groupToSelect = s.getName();
                break;
            }
        }
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupToSelect);
    }
}



