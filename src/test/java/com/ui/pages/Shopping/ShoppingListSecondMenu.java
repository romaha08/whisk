package com.ui.pages.Shopping;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ShoppingListSecondMenu {
    private SelenideElement DELETE_BUTTON = $x(".//button[@data-testid = 'shopping-list-delete-menu-button']");
    private SelenideElement CONFIRM_DELETE_BUTTON = $x(".//button[@data-testid = 'confirm-delete-button']");

    public ShoppingListSecondMenu clickDeleteListButton(){
        DELETE_BUTTON.click();
        return this;
    }

    public ShoppingList clickConfirmDeleteButton(){
        CONFIRM_DELETE_BUTTON.click();
        return new ShoppingList();
    }
}
