package org.practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RestfulApiPatchTest {
    private static final Logger logger = LoggerFactory.getLogger(RestfulApiPatchTest.class);
    private String baseUrl = "https://api.restful-api.dev/";
    private String objectId;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = baseUrl;
        logger.info("Initialized RestAssured with base URI: {}", baseUrl);
    }

    @Test(priority = 1)
    public void createObjectToGetId() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "Apple MacBook Pro 15");
        Map<String, Object> data = new HashMap<>();
        data.put("year", 2019);
        data.put("price", 1849.99);
        data.put("CPU model", "Intel Core i9");
        data.put("Hard disk size", "1 TB");
        payload.put("data", data);

        logger.info("Creating object with payload: {}", payload);
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/objects")
                .then()
                .statusCode(200)
                .extract().response();

        objectId = response.jsonPath().getString("id");
        Assert.assertNotNull(objectId, "Object ID should not be null after creation");
        logger.info("Created object with ID: {}", objectId);
    }

    @Test(priority = 2, dependsOnMethods = "createObjectToGetId")
    public void testPatchObjectSuccess() {
        Map<String, Object> patchPayload = new HashMap<>();
        patchPayload.put("name", "Apple MacBook Pro 16");

        logger.info("Patching object ID {} with payload: {}", objectId, patchPayload);
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(patchPayload)
                .when()
                .patch("/objects/" + objectId)
                .then()
                .statusCode(200)
                .extract().response();

        String updatedName = response.jsonPath().getString("name");
        Assert.assertEquals(updatedName, "Apple MacBook Pro 16", "Name should be updated");
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "Updated timestamp should be present");
        logger.info("Successfully patched object. Updated name: {}", updatedName);
    }

    @Test(priority = 3, dependsOnMethods = "createObjectToGetId")
    public void testPatchObjectInvalidId() {
        Map<String, Object> patchPayload = new HashMap<>();
        patchPayload.put("name", "Invalid Update");

        logger.info("Attempting PATCH with invalid ID 'invalid-id'");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(patchPayload)
                .when()
                .patch("/objects/invalid-id")
                .then()
                .statusCode(404)
                .extract().response();

        String errorMessage = response.jsonPath().getString("error");
        String expectedMessage = "The Object with id = invalid-id doesn't exist. Please provide an object id which exists or generate a new Object using POST request and capture the id of it to use it as part of PATCH request after that.";
        Assert.assertEquals(errorMessage, expectedMessage, "Expected error message mismatch");
        logger.info("Verified 404 response for invalid ID");
    }

    @AfterClass
    public void tearDown() {
        logger.info("API tests completed");
    }
}