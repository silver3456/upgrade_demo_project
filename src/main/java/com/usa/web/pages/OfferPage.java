package com.usa.web.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OfferPage extends BasePage {

    @FindBy(css = "span[data-auto=userLoanAmount]")
    private WebElement loan_amount;

    @FindBy(css = "span[data-auto=defaultMonthlyPayment]")
    private WebElement monthly_payment;

    @FindBy(css = "div[data-auto=defaultLoanTerm]")
    private WebElement term_elem;

    @FindBy(css = "div[data-auto=defaultLoanInterestRate]")
    private WebElement interest_rate;

    @FindBy(css = "div[data-auto=defaultMoreInfoAPR]")
    private WebElement apr_value;

    @FindBy(css = "h1[class*=title--secondary]")
    private WebElement offer_news_title;

    @FindBy(css = "button[data-auto=getDefaultLoan]")
    private WebElement select_button;

    @FindBy(css = "div.header-nav")
    private WebElement hamburger_menu;

    @FindBy(css = "ul[class*=header-nav-menu]>li:nth-child(2) a")
    private WebElement signout_link;


    public OfferPage(WebDriver driver) {
        super(driver);
        refresh();
    }


    @Step("Get loan offer details")
    public Map<String, String> getOfferInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("loanAmount", helper.getElementText(loan_amount));
        map.put("monthlyPayment", helper.getElementText(monthly_payment).substring(1));
        map.put("term", helper.getElementText(term_elem).substring(0, 2).trim());
        map.put("interestRate", getRateValue(interest_rate));
        map.put("APR", getRateValue(apr_value));
        return map;
    }

    private String getRateValue(WebElement element) {
        String res;
        String s = helper.getElementText(element);
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher m = p.matcher(s);
        if (m.find()) {
            res = m.group();
            return res;
        }
        return null;
    }


    public void logOut() {
        clickHamburgerMenu(hamburger_menu);
        clickSignOut(signout_link);
    }

    @Step("Click hamburger menu")
    private void clickHamburgerMenu(WebElement element) {
        helper.clickOnElement(element);
    }


   @Step("Click sign out link")
    private void clickSignOut(WebElement element) {
        helper.clickOnElement(element);
    }

    public Map<String, String> reFatchOfferDetails() {
        return getOfferInfo();
    }


    @Step("Validate the initial and final offer match")
    public void validateLoanOffer(Map<String, String> initialOffer, Map<String, String> finalOffer) {

        if (initialOffer.size() != finalOffer.size()) {
            Assert.fail();
        } else {
            boolean result = initialOffer.entrySet().stream()
                    .allMatch(e -> e.getValue().equals(finalOffer.get(e.getKey())));
            Assert.assertTrue(result, "Loan offers are not identical");
        }
    }


}
