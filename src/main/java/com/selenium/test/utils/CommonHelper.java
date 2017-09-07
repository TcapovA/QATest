package com.selenium.test.utils;

import static com.codeborne.selenide.Selenide.executeJavaScript;

/**
 * Created by Андрей on 07.09.2017.
 */
public class CommonHelper {
    private static CommonHelper ourInstance = new CommonHelper();

    public static CommonHelper getInstance() {
        return ourInstance;
    }

    private CommonHelper() {
    }

    public void scroolPageToBottom(){
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public String getDefaultErrorMsg(String fieldName, String expectedVal, String currentVal){
        return "Error while check field " + fieldName +
                "\nExpected value: " + expectedVal +
                "\nCurrent value: " + currentVal;
    }
}
