package com.backEnd.main;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import static com.utils.PropertyReader.getProperty;
import static com.utils.PropertyReader.readPropertiesFile;


public class Init {
    protected static Integer PORT;
    protected static String BASE_PATH;
    protected static String HOST;

    public Init() {
        readPropertiesFile();
        PORT = getProperty("server.port") != null && !getProperty("server.port").isEmpty() ? Integer.parseInt(getProperty("server.port")) : 8080;
        BASE_PATH = getProperty("server.base") != null && !getProperty("server.base").isEmpty() ? getProperty("server.base") : "";
        HOST = getProperty("server.host") != null && !getProperty("server.host").isEmpty() ? getProperty("server.host") : "";
    }

    @Step("Initialize RestAssured client")
    @BeforeSuite
    public static void setup() {
        //RestAssured.port = PORT;
        RestAssured.basePath = BASE_PATH;
        RestAssured.baseURI = HOST;
    }
}
