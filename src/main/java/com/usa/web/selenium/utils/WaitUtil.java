package com.usa.web.selenium.utils;


import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class WaitUtil {

    private WaitUtil() {
        throw new UnsupportedOperationException();
    }

    public static boolean waitForJQuery(WebDriver driver) {
        boolean readyState = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int i = 0;

        while (!readyState && i < 2) {

            try {
                readyState = (Boolean) js.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
                if (!readyState) Uninterruptibles.sleepUninterruptibly(200, TimeUnit.MILLISECONDS);
                i++;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return readyState;
    }


    public static boolean waitForAjax(WebDriver driver) {
        boolean readyState = false;

        JavascriptExecutor js = (JavascriptExecutor) driver;

        int i = 0;
        while (!readyState && i < 2) {

            try {
                readyState = js.executeScript("return document.readyState").equals("complete");
                if (!readyState) Uninterruptibles.sleepUninterruptibly(200, TimeUnit.MILLISECONDS);
                i++;

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        return readyState;
    }

    public static boolean waitForElement(WebElement el, String option, int timeOut) {
        boolean result = false;
        Date date = new Date();
        switch (option) {
            case "visible":
                while (!result && (new Date().getTime() - date.getTime() <= timeOut * 1000)) {
                    try {
                        if (el.isDisplayed()) {
                            result = true;
                        } else {
                            Uninterruptibles.sleepUninterruptibly(200, TimeUnit.MILLISECONDS);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                break;
        }
        return result;
    }

    public static boolean waitForElementWithOption(WebDriver driver, WebElement element, String option, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        switch (option) {
            case "clickable":
                wait.until(ExpectedConditions.elementToBeClickable(element));
                return true;
            case "visible":
                wait.until(ExpectedConditions.visibilityOf(element));
                return true;
            case "invisible":
                wait.until(ExpectedConditions.invisibilityOf(element));
                return true;
        }
        return false;
    }

    public static void waitForElement(WebDriver driver, String locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated((By.cssSelector(locator))));
    }
}
