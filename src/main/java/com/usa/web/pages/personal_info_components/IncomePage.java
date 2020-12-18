package com.usa.web.pages.personal_info_components;

import com.usa.web.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IncomePage extends BasePage {

    @FindBy(css = "input[name=borrowerIncome]")
    private WebElement individual_income;

    @FindBy(css = "input[name=borrowerAdditionalIncome]")
    private WebElement additional_income;

    @FindBy(css = "button[type=submit]")
    private WebElement continue_button;

    @FindBy(css = "h1[class*=title--secondary]")
    private WebElement title;


    public IncomePage(WebDriver driver) {
        super(driver);
        refresh();
    }


    @Step("Enter individual annual income and additional annual income, then click Continue")
    public CredentialsPage goToCredentialsPage(int indivIncome, int additIncome) {
        helper.enterText(individual_income, String.valueOf(indivIncome));
        helper.enterText(additional_income, String.valueOf(additIncome));
        helper.clickOnElement(title);
        helper.clickOnElement(continue_button);
        return new CredentialsPage(driver);
    }


}
