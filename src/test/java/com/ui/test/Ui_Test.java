package com.ui.test;

import com.enums.Product;
import com.ui.pages.MainPage;
import com.ui.pages.Shopping.ShoppingList;
import com.utils.TestUtils;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class Ui_Test extends TestBase {
    private MainPage mainPage = new MainPage();
    private ShoppingList shoppingList = new ShoppingList();

    @Test
    public void test1() {
        List<Product> products = Arrays.asList(Product.MILK, Product.BREAD, Product.BUTTER, Product.CHEESE, Product.EGGS);
        String shoppingListName = "TestList1" + TestUtils.generateInt(4);
        String email = "qaTest1" + TestUtils.generateInt(4) + "@" + "mail.ru";
        loginCreateShoppingList(email, shoppingListName);
        shoppingList.addItemsToShoppingList(shoppingListName, products)
                .checkProductsAddedToList(shoppingListName, products);
    }

    @Test
    public void test2() {
        String shoppingListName = "TestList2" + TestUtils.generateInt(4);
        String email = "qaTest2" + TestUtils.generateInt(4) + "@" + "mail.ru";
        loginCreateShoppingList(email, shoppingListName);
        shoppingList.deleteShoppingList(shoppingListName)
                .checkShoppingListDeleted(shoppingListName);
    }

    private void loginCreateShoppingList(String email, String listName){
        mainPage.loginWithEmail(email)
                .navigateToShoppingList()
                .createShoppingList(listName)
                .checkShoppingListCreated(listName);
    }
}
