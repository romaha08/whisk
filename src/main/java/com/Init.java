package com;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.BeforeSuite;

import static com.utils.PropertyReader.getProperty;
import static com.utils.PropertyReader.readPropertiesFile;

public class Init {
    public static final long TIMEOUT_SELENIDE = 10000;

    @BeforeSuite
    protected void init() {
        readPropertiesFile();
        Configuration.baseUrl = "https://" + getProperty("baseUrl");
        Configuration.timeout = TIMEOUT_SELENIDE;
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("selenide.browser", "Chrome");
        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.startMaximized = true;
        Configuration.pageLoadStrategy = "none";
    }
}
