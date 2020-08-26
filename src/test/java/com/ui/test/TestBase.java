package com.ui.test;

import com.Init;
import com.ui.pages.MainPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase extends Init {
    @BeforeMethod
    public static MainPage openMainPage() {
        open(baseUrl);
        return new MainPage().waitForPageLoaded();
    }

    @AfterMethod(alwaysRun = true)
    public static void closeDriver(){
        closeWebDriver();
    }
}
