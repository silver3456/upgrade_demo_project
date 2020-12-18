package com.usa.web.selenium.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;

public class ElementHelper {
    private WebDriver driver;
    private static Logger LOG = Logger.getLogger(ElementHelper.class.getName());

    public ElementHelper(WebDriver driver) {
        this.driver = driver;
    }


    public void enterText(WebElement locator, String text) {
        LOG.info(format("Clear and set text: %s to an element with locator: %s", text, locator));
        locator.clear();
        locator.sendKeys(text);
    }


    public boolean clickOnElement(WebElement locator) {
        LOG.info("CLick on element with locator: " + locator);
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            locator.click();
            return true;
        } catch (Exception ex) {
            LOG.info(ex.getMessage());
            return false;
        }
    }


    public boolean clickElementWithOffset(WebElement element, int xOffSet, int yOffSet) {
        try {
            if (WaitUtil.waitForElementWithOption(driver, element, "clickable", 10)) {
                new Actions(driver).moveToElement(element, xOffSet, yOffSet).click().perform();
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clickOnElementWithOffset(WebElement element) {
        Actions actions = new Actions(driver);

        for (int x = 0; x < element.getSize().getWidth(); x++) {
            for (int y = 0; y < element.getSize().getHeight(); y++) {
                actions.moveToElement(element).moveByOffset(x, y).click().build().perform();
                if (element.isSelected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getElementText(WebElement element) {
        try {
            return element.getText();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return null;
        }
    }

}
