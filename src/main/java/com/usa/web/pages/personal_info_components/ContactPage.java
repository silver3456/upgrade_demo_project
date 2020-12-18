package com.usa.web.pages.personal_info_components;

import com.usa.web.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactPage extends BasePage {


    @FindBy(css = "input[name=borrowerFirstName]")
    private WebElement first_name;

    @FindBy(css = "input[name=borrowerLastName]")
    private WebElement last_name;


    @FindBy(css = "input[name=borrowerStreet]")
    private WebElement home_address;


    @FindBy(css = "input[name=borrowerCity]")
    private WebElement city;


    @FindBy(css = "input[name^=borrowerState]")
    private WebElement state;


    @FindBy(css = "input[name=borrowerZipCode]")
    private WebElement zip_code;


    @FindBy(css = "input[name^=borrowerDateOfBirth]")
    private WebElement date_of_birth;

    @FindBy(css = "button[type=submit]")
    private WebElement continue_button;

    private static final String PATTERN_ADDRESS = "//*[@class = 'geosuggest__item__matched-text']";



    public ContactPage(WebDriver driver) {
        super(driver);
        refresh();
    }



    @Step("Enter first name")
    public ContactPage enterFirstName(String fName) {
        helper.enterText(first_name, fName);
        return this;
    }

    @Step("Enter last name")
    public ContactPage enterLastName(String lName) {
        helper.enterText(last_name, lName);
        return this;
    }



    @Step("Enter home address")
    public ContactPage enterHomeAddress(String address) {
        helper.enterText(home_address, address);
        driver.findElement(By.xpath((String.format(PATTERN_ADDRESS, address)))).click();
        return this;
    }


    @Step("Enter city name")
    public ContactPage enterCityName(String cityName) {
        helper.enterText(city, cityName);
        return this;
    }

    @Step("Enter state")
    public ContactPage enterState(String stateName) {
        helper.enterText(state, stateName);
        return this;
    }

    @Step("Enter zip code")
    public ContactPage enterZipCode(int zip) {
        helper.enterText(zip_code, String.valueOf(zip));
        return this;
    }

    @Step("Enter date of birth")
    public ContactPage enterDateOfBirth(String dob) {
        helper.enterText(date_of_birth, dob);
        return this;
    }

    @Step("Click Continue button")
    public ContactPage clickContinue() {
        helper.clickOnElement(continue_button);
        return this;
    }

}
