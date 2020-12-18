package com.usa.web.selenium;

import static com.usa.web.selenium.utils.PropertyLoader.getBaseUrl;
import static com.usa.web.selenium.utils.PropertyLoader.getLoginUrl;

import com.usa.web.pages.LandingPage;
import com.usa.web.pages.personal_info_components.ContactPage;
import com.usa.web.pages.LoginPage;
import com.usa.web.pages.OfferPage;
import com.usa.web.pages.personal_info_components.CredentialsPage;
import com.usa.web.pages.personal_info_components.IncomePage;
import com.usa.web.selenium.utils.StringRandomGenerator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class LoanPurposeTest extends TestRunner {

    private LandingPage landingPage;
    private ContactPage contactPage;
    private IncomePage incomePage;
    private CredentialsPage credentialsPage;
    private OfferPage offerPage;
    private LoginPage loginPage;

    private static final String BASE_URL = getBaseUrl();
    private static final String LOGIN_URL = getLoginUrl();

    @BeforeClass
    public void initPages() {
        landingPage = new LandingPage(driver);
        contactPage = new ContactPage(driver);
        incomePage = new IncomePage(driver);
        credentialsPage = new CredentialsPage(driver);
        offerPage = new OfferPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test(testName = "testLoanPrequalificationForm")
    public void testLoanPrequalificationForm() {
        landingPage.open(BASE_URL);
        landingPage.goToLoanPurposePage(2000, "Large Purchase");

        String email = StringRandomGenerator.generateEmail();
        String password = StringRandomGenerator.provideValidPassword(10);

        contactPage
                .enterFirstName("Alex")
                .enterLastName("Smith")
                .enterHomeAddress("333 1st Street")
                .enterCityName("San Francisco")
                .enterState("CA")
                .enterZipCode(94121)
                .enterDateOfBirth("12/11/1983")
                .clickContinue();

        incomePage.goToCredentialsPage(150000, 6000);
        credentialsPage.checkYourRate(email, password);

        Map<String, String> initialOffer = offerPage.getOfferInfo();

        offerPage.logOut();
        landingPage.open(LOGIN_URL);

        loginPage.signIn(email, password);

        Map<String, String> confirmedOffer = offerPage.reFatchOfferDetails();

        offerPage.validateLoanOffer(initialOffer, confirmedOffer);

    }


}
