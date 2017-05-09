package test.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().allC().size() == 0) {
            app.contact().createC(new ContactData().withName("Masha").withLastname("Ivanova")
                    .withHomePhone("111").withMobilenumber("79773456757").withWorkPhone("333")
                    .withCityname("neverland").withEmail("gav@gav.ru").withEmail2("gav2@gav.ru")
            .withEmail3("gav3@gav.ru"));
        }
    }

    @Test
    public void testContactInfo() {
        app.goTo().homePage();
        ContactData contact = app.contact().allC().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmailes(contactInfoFromEditForm)));
        assertThat(contact.getCityname(), equalTo(contactInfoFromEditForm.getCityname()));
    }
    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilenumber(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactInfoTests::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmailes(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhones(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
