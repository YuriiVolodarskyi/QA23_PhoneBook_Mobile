package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthentificationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactsTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new AuthentificationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("alimych65@gmail.com")
                        .password("Yv030665@")
                        .build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
    }

    @Test
    public void createNewContactSuccess(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Vasya")
                .lastName("Pupkin")
                .email("vasya" + i + "@gmail.com")
                .phone("123456" + i)
                .address("Haifa")
                .description("Friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());
    }
    @Test
    public void createNewContactSuccessReq(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Vasya")
                .lastName("Pupkin")
                .email("vasya" + i + "@gmail.com")
                .phone("123456" + i)
                .address("Haifa")
                .description("Friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());

    }
    @Test
    public void createContactWithEmptyName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Pupkin")
                .email("vasya@gmail.com")
                .phone("12345611111")
                .address("Haifa")
                .description("Empty Name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainText("{name=must not be blank");
    }
    @Test
    public void createContactWithEmptyLastName(){
        Contact contact = Contact.builder()
                .name("Vasya")
                .lastName("")
                .email("vasya@gmail.com")
                .phone("123456123456")
                .address("Haifa")
                .description("Empty Name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainText("{last name=must not be blank");
    }

    @AfterClass
    public void postCondition(){
        new ContactListScreen(driver).logout();

    }
}
