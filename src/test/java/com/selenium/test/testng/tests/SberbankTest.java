package com.selenium.test.testng.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.selenium.test.hooks.BaseTest;
import com.selenium.test.pages.SberbankPage;
import com.selenium.test.utils.AutotestError;
import com.selenium.test.utils.CommonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Андрей on 07.09.2017.
 */
public class SberbankTest extends BaseTest {

    SberbankPage sberPage;
    CommonHelper helper = CommonHelper.getInstance();

    @BeforeMethod
    public void openPage(){
        sberPage = open("http://www.sberbank.ru/ru/person", SberbankPage.class);
    }

    @Test
    public void checkRegionAndFooter() throws AutotestError {
        sberPage.currentRegion.click();
        sberPage.enterRegion.setValue("Нижегородская область");

        final String region = "Нижегородская область";
        Optional<SelenideElement> findedRegion = $$(By.className("region-search-box__option")).stream().
                filter(el -> el.getText().equalsIgnoreCase(region))
                .findFirst();
        if(!findedRegion.isPresent()){
            throw new AutotestError("Error while trying to find region " + region);
        }
        findedRegion.get().click();
        sberPage.waitPageLoading();

        String regionElementText = sberPage.currentRegion.getText();
        Assert.assertTrue(region.equals(regionElementText), helper.getDefaultErrorMsg("region", region, regionElementText));
    }

    @Test
    public void checkSocialGroupsLinks() throws AutotestError{
        helper.scroolPageToBottom();
        SelenideElement socailGroupsElement = $(By.xpath(".//*[@class='social__list']"));
        List<String> linksToSocialGroups = Arrays.asList("https://www.facebook.com/bankdruzey", "http://twitter.com/sberbank/",
                "http://twitter.com/sberbank/", "http://www.youtube.com/sberbank", "http://instagram.com/sberbank", "http://vk.com/sberbank",
                "https://ok.ru/sberbank");

        StringBuilder errMsg = new StringBuilder("");
        for(String link: linksToSocialGroups){
            try {
                socailGroupsElement.$(By.xpath(".//a[@href='" + link + "']")).should(Condition.exist);
            } catch (NoSuchElementException e) {
                errMsg.append("Error during test links to socialGroups. Can't find expected link: " + link + "\n");
            }
        }
        if(errMsg.length() > 0){
            throw new AutotestError(errMsg.toString());
        }
    }
}