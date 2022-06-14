package com.bridgelabz.petstore.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

public class PetStoreAutomation {

    @Test
    public void uploadImageInPetStore(){
        System.out.println("-----------------Image upload-----------------");
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

    @Test
    public void userLoginWithPathParams(){
        System.out.println("-----------------User Login With PathParams-----------------");
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .pathParam("username", "ashok2022")
                .pathParam("password", "0000")
                .when()
                .get("https://petstore.swagger.io/v2/user/login?username={username}&password={password}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void userLoginWithQueryParams(){
        System.out.println("-----------------User Login With QueryParams-----------------");
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .queryParam("username", "ashok2022")
                .queryParam("password", "0000")
                .when()
                .get("https://petstore.swagger.io/v2/user/login");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void getUserByUsername(){
        System.out.println("-----------------Get User By Username-----------------");
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .pathParam("username", "ashok2022")
                .when()
                .get("https://petstore.swagger.io/v2/user/{username}");
        response.prettyPrint();
        String name = response.path("username");
        System.out.println("Username : " + name);
        response.then().assertThat().statusCode(200);
    }
}
