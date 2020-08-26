package com.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

public class PageBase {
    public static SelenideElement clearAndFillTextField(SelenideElement field, String value) {
        field.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        field.shouldBe(Condition.visible).val(value);
        return field;
    }
}
