package com.usa.web.pages.personal_info_components;

import com.usa.web.pages.BasePage;
import com.usa.web.pages.OfferPage;
import com.usa.web.selenium.utils.WaitUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class CredentialsPage extends BasePage {

    @FindBy(css = "input[autocomplete=email]")
    private WebElement email_address_field;

    @FindBy(css = "input[type=password]")
    private WebElement password_field;


    @FindBy(css = "button[type=submit]")
    private WebElement check_rate_button;


    @FindBy(css = "input[name=agreements]")
    private WebElement agree_checkbox;


    private final String CHECK_RATE_BUTTON = "button[type=submit]";

    public CredentialsPage(WebDriver driver) {
        super(driver);
        refresh();
    }


    @Step("Enter email and password, then click Check Your Rate")
    public OfferPage checkYourRate(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        selectCheckbox(agree_checkbox);
        helper.clickOnElement(check_rate_button);
        WaitUtil.waitForElement(driver, CHECK_RATE_BUTTON);
        return new OfferPage(driver);
    }


    @Step("Enter email")
    private void enterEmail(String email) {
        helper.enterText(email_address_field, email);
    }


    @Step("Enter password")
    private void enterPassword(String password) {
        helper.enterText(password_field, password);
    }

    @Step("Check the box for Terms of Use")
    private void selectCheckbox(WebElement element) {
        helper.clickOnElementWithOffset(element);
    }



}
