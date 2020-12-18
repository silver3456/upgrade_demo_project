package com.usa.web.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class LandingPage extends BasePage {

    @FindBy(css = "input[id^=aria-autogenerated]")
    private WebElement loan_amount;

    @FindBy(css = "select[aria-label ='Loan Purpose']")
    private WebElement loan_purpose_dropdown;

    @FindBy(css = "button[class^='sc-gXfWUo']")
    private WebElement check_your_rate_button;


    public LandingPage(WebDriver driver) {
        super(driver);
        refresh();
    }

    public LandingPage open(String url) {
        LOG.info("Open with url: " + url);
        driver.get(url);
        return this;
    }

    @Step("Enter loan amount and loan purpose, then click Check Your Rate button")
    public void goToLoanPurposePage(int amount, String loanPurpose) {
        LOG.info("Navigate to Loan purpose page");
        helper.enterText(loan_amount, String.valueOf(amount));
        selectLoanPurpose(loanPurpose);
        helper.clickOnElement(check_your_rate_button);
    }

    private void selectLoanPurpose(String option) {
        Select select = new Select(loan_purpose_dropdown);
        select.selectByVisibleText(option);
    }
}