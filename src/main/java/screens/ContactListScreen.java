package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.awt.*;
import java.util.List;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;
    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement menuOptions;
    @FindBy(xpath = "//*[@content-desc='More options']")
    List<AndroidElement> menuOptionsList;
    @FindBy(xpath = "//*[@text = 'Logout']")
    AndroidElement logoutBtn;
    @FindBy(xpath = "//*[@content-desc='add']")
    AndroidElement plusBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<AndroidElement> contactNameList;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<AndroidElement> contactList;
    @FindBy(id = "android:id/button1")
    AndroidElement yesBtn;
    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    AndroidElement noContactsHereTextView;

    int countBefore;
    int countAfter;

    public boolean isActivityTitleDisplayed(String text) {
        //return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }

    public AuthentificationScreen logout() {
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthentificationScreen(driver);
    }

    public AuthentificationScreen logout2() {
        if (isElementDisplayed(menuOptions)) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthentificationScreen(driver);
    }

    public AuthentificationScreen logout3() {
        if (isElementPresentList(menuOptionsList)) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthentificationScreen(driver);
    }

    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public AddNewContactScreen openContactForm() {
        if (activityTextView.getText().equals("Contact list")) {
            should(activityTextView, 5);
            plusBtn.click();
        }
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isContactAddedByName(String name, String lastName) {
        isShouldHave(activityTextView, "Contact list", 5);
        boolean isPresent = false;
        for (AndroidElement el : contactNameList) {
            if (el.getText().equals(name + " " + lastName)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }

    public ContactListScreen deleteFirstContact() {
        isActivityTitleDisplayed("Contact list");
        countBefore = contactList.size();
        AndroidElement first = contactList.get((0));
        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX() + rectangle.getWidth() / 8;
        int xTo = rectangle.getWidth() - xFrom;
        int y = rectangle.getY() + rectangle.getHeight() / 2;
        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom, y))
                .moveTo(PointOption.point(xTo, y))
                .release()
                .perform();
        should(yesBtn, 8);
        yesBtn.click();
        shouldLessOne(contactList, countBefore);
        countAfter = contactList.size();
        return this;
    }

    public ContactListScreen isListSizeLessOnOne() {
        Assert.assertEquals(countBefore - countAfter, 1);
        return this;
    }
    public ContactListScreen removeAllContacts(){
        pause(1000);
        while (contactList.size()>0){
            deleteFirstContact();
        }
        return this;
    }
    public ContactListScreen isNoContactsHere(){
        isShouldHave(noContactsHereTextView, "No contacts. Add One more!", 10);
        return this;
    }

}
