package com.backEnd.main;

import com.backEnd.main.entity.Param;
import com.backEnd.main.enums.RequestType;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.params.CoreConnectionPNames;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.utils.PropertyReader.getProperty;
import static io.restassured.RestAssured.given;
import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT;
import static org.apache.http.params.CoreConnectionPNames.SO_TIMEOUT;

public class RestApi extends Init {
    private String TOKEN = getProperty("token") != null && !getProperty("token").isEmpty() ? getProperty("token") : "";

    public Response executeRequest(RequestType requestType, String requestUri, List<Param> params) {
        return executeRequest(requestType, requestUri, params, null);
    }

    public Response executeRequest(RequestType requestType, String requestUri, Object object) {
        return executeRequest(requestType, requestUri, null, object);
    }

    public Response executeRequest(RequestType requestType, String requestUri, List<Param> params, Object object) {
        switch (requestType) {
            case GET:
                return getRequest(requestUri, params);
            case PUT:
                return putRequest(requestUri);
            case POST:
                return postRequest(requestUri, params, object);
            case DELETE:
                return deleteRequest(requestUri, params);

            default:
                throw new AssertionError("Cannot Execute Request. Unknown request type");
        }
    }

    private Response postRequest(String requestUri, List<Param> params, Object object) {
        RequestSpecification requestSpecification = getRequestSpecificationWithParams(params);

        if (object != null) {
            requestSpecification.body(object);
        }
        return requestSpecification.post(requestUri);
    }

    private Response getRequest(String requestUri, List<Param> params){
        return getRequestSpecificationWithParams(params).get(requestUri);
    }

    private Response putRequest(String requestUri){
        return given()
                .baseUri(HOST)
                .headers(
                        "Authorization",
                        "Bearer " + TOKEN,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .put(requestUri);
    }

    private Response deleteRequest(String requestUri, List<Param> params){
        return getRequestSpecificationWithParams(params).delete(requestUri);
    }

    private RequestSpecification getRequestSpecificationWithParams(List<Param> params) {
        RequestSpecification requestSpecification = given()
                .baseUri(HOST)
                .headers(
                        "Authorization",
                        "Bearer " + TOKEN,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.ANY);

        if (params != null) {
            for (Param param : params) {
                if(param.getHeaders() != null && param.getHeaders().size() > 0){
                    for (Header hed : param.getHeaders()) {
                        requestSpecification.header(hed);
                    }
                }
                Map<String, String> paramsToSet = new HashMap<>();
                paramsToSet.put(param.getName(), param.getValue());
                switch (param.getType()) {
                    case PARAMS:
                        requestSpecification.params(paramsToSet);
                        break;

                    case FORM:
                        requestSpecification.formParams(paramsToSet);
                        break;

                    case PATH:
                        requestSpecification.pathParams(paramsToSet);
                        break;

                    case QUERY:
                        requestSpecification.queryParams(paramsToSet);
                        break;
                }
            }
        }

        return requestSpecification;
    }

    @Step("Check Response code")
    private void checkResponseStatus(Response response, int status, String message){
        Assert.assertEquals(response.getStatusCode(), status, "Status of response is not: " + status + "\n" + message);
    }

    public void checkResponseOk(Response response){
        checkResponseStatus(response, 200, "");
    }

    public void checkResponseNotFound(Response response){
        checkResponseStatus(response, 400, "");
    }

    public void checkResponseOk(Response response, String message){
        checkResponseStatus(response, 200, message);
    }
}
