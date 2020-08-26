package com.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class WelcomePopup {
    private SelenideElement POPUP = $x(".//div[contains(@class, 'modal-mobile-popup')]");
    private SelenideElement LETS_GET_COOKING_BUTTON = POPUP.$x(".//button[@data-testid = 'community-onboarding-continue']");

    public SelenideElement getPopup(){
        return POPUP;
    }

    public WelcomePopup clickLetsCooking(){
        LETS_GET_COOKING_BUTTON.click();
        return this;
    }
}
