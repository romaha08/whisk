package com.ui.pages.Shopping;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.enums.Product;
import com.utils.Waiter;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ShoppingList {
    private CreateShoppingListPopup createShoppingListPopup = new CreateShoppingListPopup();
    private ShoppingListSecondMenu shoppingListSecondMenu = new ShoppingListSecondMenu();
    private SelenideElement CREATE_NEW_LIST_BUTTON = $x(".//a[@data-testid = 'create-new-shopping-list-button']");
    private SelenideElement ADD_ITEM_INPUT = $x(".//input[@data-testid = 'desktop-add-item-autocomplete']");
    private ElementsCollection ALL_LISTS = $$x(".//div[@data-testid='shopping-lists-list-name']");
    private ElementsCollection PRODUCTS_IN_THE_LIST = $$x(".//div[@data-testid = 'shopping-list-item']");

    public ShoppingList waitPageLoaded(){
        Waiter.waitForElementAppears(CREATE_NEW_LIST_BUTTON);
        return this;
    }

    @Step("Shopping: Create new Shopping list")
    public ShoppingList createShoppingList(String name) {
        CREATE_NEW_LIST_BUTTON.click();
        createShoppingListPopup.waitPopupLoaded()
                .setListNameOrStay(name)
                .clickAdd();
        waitPageLoaded();
        return this;
    }

    @Step("Shopping: Check Shopping list created")
    public ShoppingList checkShoppingListCreated(String listName){
        getListByName(listName).shouldBe(Condition.visible);
        return this;
    }

    @Step("Shopping: Check Shopping list created")
    public ShoppingList checkShoppingListDeleted(String listName){
        getListByName(listName).shouldNotBe(Condition.visible);
        return this;
    }

    @Step("Shopping: Delete Shopping list")
    public ShoppingList deleteShoppingList(String listName) {
        getListByName(listName).parent().parent().parent().$x(".//button").click();
        shoppingListSecondMenu.clickDeleteListButton()
                .clickConfirmDeleteButton();
        return this;
    }

    @Step("Shopping: Add items to Shopping list")
    public ShoppingList addItemsToShoppingList(String listName, List<Product> products) {
        SelenideElement list = getListByName(listName);
        list.click();
        for (Product pr : products) {
            ADD_ITEM_INPUT.click();
            ADD_ITEM_INPUT.clear();
            ADD_ITEM_INPUT.setValue(pr.getValue());
            getProductInAutoComplete(pr.getValue()).click();
        }
        list.click();
        return this;
    }

    public ShoppingList checkProductsAddedToList(String listName, List<Product> products){
        SoftAssert sa = new SoftAssert();
        SelenideElement list = getListByName(listName);
        list.click();
        for (Product pr : products) {
            boolean isExists = PRODUCTS_IN_THE_LIST.findBy(Condition.exactText(pr.getValue())).isDisplayed();
            sa.assertTrue(isExists, "Product: " + pr.getValue() + " is not added to shopping list");
        }
        sa.assertAll();
        return this;
    }

    private SelenideElement getListByName(String name){
        return ALL_LISTS.findBy(Condition.text(name));
    }

    private SelenideElement getProductInAutoComplete(String productName){
        Waiter.waitForElementAppears($$x(".//div[@data-testid = 'desktop-add-item-autocomplete']//div/span"));
        ElementsCollection allProducts = $$x(".//div[@data-testid = 'desktop-add-item-autocomplete']//div/span");
        return allProducts.findBy(Condition.exactText(productName));
    }
}
