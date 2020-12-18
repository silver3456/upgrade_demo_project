package com.usa.web.pages;

import com.usa.web.selenium.utils.ElementHelper;
import com.usa.web.selenium.utils.WaitUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;
    protected ElementHelper helper;
    protected Logger LOG;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        helper = new ElementHelper(driver);
        LOG = Logger.getLogger(this.getClass().getName());
        refresh();
    }

    public void refresh() {
        WaitUtil.waitForJQuery(driver);
        WaitUtil.waitForAjax(driver);
        PageFactory.initElements(driver, this);
    }
}
