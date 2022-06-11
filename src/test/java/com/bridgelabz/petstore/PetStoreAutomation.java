package com.bridgelabz.petstore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

public class PetStoreAutomation {

    @Test
    public void uploadImageInPetStore(){
        File file = new File("C:\\Users\\BridgeLabz Solutions\\Downloads\\Images\\01.jpg");
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("multipart/form-data")
                .multiPart(file)
                .when()
                .post("https://petstore.swagger.io/v2/pet/1/uploadImage");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
}
