package com.ui.pages.Shopping;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.ui.pages.PageBase;
import org.testng.util.Strings;

import static com.codeborne.selenide.Selenide.$x;

public class CreateShoppingListPopup extends PageBase {
    private SelenideElement SHOPPING_LIST_INPUT = $x(".//input[@data-testid = 'UI_KIT_INPUT']");
    private SelenideElement ADD_BUTTON = $x(".//button[@data-testid = 'create-new-shopping-list-create-button']");
    private SelenideElement CANCEL_BUTTON = $x(".//button[@data-testid = 'create-new-shopping-list-cancel-button']");

    public CreateShoppingListPopup waitPopupLoaded(){
        SHOPPING_LIST_INPUT.shouldBe(Condition.visible);
        ADD_BUTTON.shouldBe(Condition.visible);
        CANCEL_BUTTON.shouldBe(Condition.visible);
        return this;
    }

    public CreateShoppingListPopup setListNameOrStay(String name){
        if(!Strings.isNullOrEmpty(name)){
            clearAndFillTextField(SHOPPING_LIST_INPUT, name);
        }
        return this;
    }

    public CreateShoppingListPopup clickAdd(){
        ADD_BUTTON.click();
        return this;
    }

    public CreateShoppingListPopup clickCancel(){
        CANCEL_BUTTON.click();
        return this;
    }
}
