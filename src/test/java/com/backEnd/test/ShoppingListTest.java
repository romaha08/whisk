package com.backEnd.test;

import com.backEnd.main.entity.Param;
import com.backEnd.main.entity.ShoppingList;
import com.backEnd.main.enums.RequestParamsType;
import com.backEnd.main.enums.RequestType;
import com.google.gson.JsonObject;
import com.utils.JsonUtils;
import com.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListTest extends TestBase {
    private String shoppingListUri = "/list/v2";

    @Test
    public void createShoppingListTest(){
        SoftAssert sa = new SoftAssert();
        String shoppingListName = "TestList1" + TestUtils.generateInt(4);
        String id = createShoppingList(shoppingListName);

        JsonObject response = getShoppingList(id, true);

        String gottenId = response.get("list").getAsJsonObject().get("id").getAsString();
        JsonObject content = response.get("content").getAsJsonObject();
        sa.assertEquals(gottenId, id, "Get Shopping List: Id is incorrect");
        sa.assertEquals(content.keySet().size(), 0, "Get Shopping List: Content is not empty");
        sa.assertAll();
    }

    @Test
    public void deleteShoppingListTest(){
        String shoppingListName = "TestList1" + TestUtils.generateInt(4);
        String id = createShoppingList(shoppingListName);

        deleteShoppingList(id);

        JsonObject response = getShoppingList(id, false);
        String message = response.get("code").getAsString();
        Assert.assertEquals(message, "shoppingList.notFound", "After trying to get deleted shopping list - message is incorrect");
    }

    private JsonObject getShoppingList(String id, boolean shouldExist){
        List<Param> params = new ArrayList<>();
        params.add(new Param(RequestParamsType.PATH, "id", id));
        Response response = restClient.executeRequest(RequestType.GET, shoppingListUri + "/{id}", params);
        String responseBody = response.getBody().asString();
        if(shouldExist) {
            restClient.checkResponseOk(response);
        }else{
            restClient.checkResponseNotFound(response);
        }
        return JsonUtils.getJsonFromString(responseBody);
    }

    private String createShoppingList(String shoppingListName){
        ShoppingList shoppingList = new ShoppingList(shoppingListName);
        Response response = restClient.executeRequest(RequestType.POST, shoppingListUri, shoppingList);
        String responseBody = response.getBody().asString();
        restClient.checkResponseOk(response);
        JsonObject json = JsonUtils.getJsonFromString(responseBody);
        return json.get("list").getAsJsonObject().get("id").getAsString();
    }

    private void deleteShoppingList(String id){
        List<Param> params = new ArrayList<>();
        params.add(new Param(RequestParamsType.PATH, "id", id));
        Response response = restClient.executeRequest(RequestType.DELETE, shoppingListUri + "/{id}", params);
        restClient.checkResponseOk(response);
    }
}
