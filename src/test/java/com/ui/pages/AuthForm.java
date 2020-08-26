package com.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AuthForm extends PageBase {
    private SelenideElement FORM = $x(".//div[@data-testid = 'authentication-modal']");
    private SelenideElement EMAIL_INPUT = FORM.$x(".//div[@data-testid = 'email-phone-number-auth-input']//input");
    private SelenideElement CONTINUE_BUTTON = FORM.$x(".//button[@data-testid = 'auth-continue-button']");

    public SelenideElement getForm(){
        return FORM;
    }

    public AuthForm fillEmail(String email){
        clearAndFillTextField(EMAIL_INPUT, email);
        return this;
    }

    public AuthForm clickContinueButton(){
        CONTINUE_BUTTON.click();
        return this;
    }
}
