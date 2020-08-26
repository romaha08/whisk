package com.backEnd.test;

import com.backEnd.main.Init;
import com.backEnd.main.RestApi;
import org.testng.annotations.*;

public class TestBase extends Init {
    protected RestApi restClient;

    @BeforeSuite
    public void init(){
        restClient = new RestApi();
    }
}
