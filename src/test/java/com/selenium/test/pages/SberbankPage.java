package com.selenium.test.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.selenium.test.utils.AutotestError;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Андрей on 07.09.2017.
 */
public class SberbankPage {

    @FindBy(className = "region-list__name")
    public SelenideElement currentRegion;

    @FindBy(xpath = ".//*[@placeholder='Введите название региона']")
    public SelenideElement enterRegion;

    private final int defaultTimeoutPageToLoad = 10;

    public void waitPageLoading(){
        waitPageLoading(defaultTimeoutPageToLoad);
    }

    public void waitPageLoading(int timeoutInSec){
        $(By.xpath(".//img[@title='Sberbank']")).waitUntil(Condition.visible, timeoutInSec * TimeUnit.MILLISECONDS.toSeconds(timeoutInSec));
    }
}