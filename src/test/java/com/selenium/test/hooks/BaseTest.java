package com.selenium.test.hooks;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Андрей on 07.09.2017.
 */
public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void setUp(){
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/selenium/resources/webdrivers/chromedriver.exe");
    }
}