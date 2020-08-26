package com.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.enums.MenuItem;
import com.ui.pages.Shopping.ShoppingList;
import com.utils.Waiter;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private AuthForm authForm = new AuthForm();
    private WelcomePopup welcomePopup = new WelcomePopup();
    private SelenideElement ADD_TO_CART_BUTTON = $x(".//button[@data-testid = 'home-fab-button']");

    public MainPage waitForPageLoaded() {
        Waiter.waitForElementAppears(authForm.getForm());
        return this;
    }

    @Step("Main page: Login With Email {0}")
    public MainPage loginWithEmail(String email){
        authForm.fillEmail(email)
                .clickContinueButton();
        Waiter.waitForElementAppears(welcomePopup.getPopup());
        welcomePopup.clickLetsCooking();
        ADD_TO_CART_BUTTON.shouldBe(Condition.visible);
        return this;
    }

    @Step("Main page: Click on menu 'Shopping'")
    public ShoppingList navigateToShoppingList() {
        navigateToMenu(MenuItem.SHOPPING);
        return new ShoppingList().waitPageLoaded();
    }

    private void navigateToMenu(MenuItem menuItem){
        String locator = ".//div[@data-testid = '" + menuItem.getValue().toLowerCase() + "-nav-link']/a";
        if(menuItem.equals(MenuItem.SHOPPING)){
            locator = ".//div[@data-testid = '" + menuItem.getValue().toLowerCase() + "-list-nav-link']/a";
        }
        $x(locator).click();
    }
}
