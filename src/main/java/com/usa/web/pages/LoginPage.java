package com.usa.web.pages;

import com.usa.web.selenium.utils.WaitUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {

    @FindBy(css = "input[name=username]")
    private WebElement email_field;

    @FindBy(css = "input[name=password]")
    private WebElement password_field;

    @FindBy(css = "button[data-auto=login]")
    private WebElement signIn_button;

    private static final String OFFER_PAGE = "https://www.credify.tech/funnel/offer-page";
    private static final String SIGN_IN_BUTTON = "button[data-auto=login]";


    public LoginPage(WebDriver driver) {
        super(driver);
        refresh();
    }


    @Step("Navigate to Log in page")
    public void signIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn(signIn_button);
        WaitUtil.waitForElement(driver, SIGN_IN_BUTTON);
        Assert.assertEquals(driver.getCurrentUrl(), OFFER_PAGE, "User is not " +
                "signed in to the Offer page");
    }

    @Step("Enter email")
    private void enterEmail(String name) {
        helper.enterText(email_field, name);
    }

    @Step("Enter password")
    private void enterPassword(String password) {
        helper.enterText(password_field, password);
    }

    @Step("Click sign in")
    private void clickSignIn(WebElement element) {
        helper.clickOnElement(element);
    }
}
