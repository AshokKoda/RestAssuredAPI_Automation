package com.bridgelabz.restassuredapi.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestAssured_API_Automation {
    String token;

    @BeforeTest
    public void getToken(){
        token = "Bearer BQBnuMRxn5FghAlhyho3ghVhfUecW4qmGfz_JA3VRMYRbOWzcwjV_nBAcyRR6lNoFGlPw0Udp8F6lPON0UPJENhj4ctUVRUzM9hV3iTw53fqoctje4BrWFeAV2SlIHl7mXVpQN6HGv9uevxkOHbTdQaYRHjFnX1ZfABbWNLUmzDhGDivKSSXeN6YRgCHBCucA7Gk5scTeG-wclVcFN4A8BlGx2Y6RBcskHB-hBAgpbGhyHnbIZzsV0DAEptJrVxgV1MrMd2zzBhYjuuYG1sZftqyk5plxfCUS6YG7DHPA2bOWLIe2_Ff";
    }

    @Test
    public void getCurrentUserProfile(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/me");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void getCurrentUserProfileWithId(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/users/313ezwhpfwgm7kspa45ceyqhgtu4");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void createNewPlaylist(){
        String requestBody = "{\"name\":\"Telugu New Collection\",\n\"description\":\"Telugu playlist description\",\n\"public\":\"false\"}";

        Response response = RestAssured.given()
                .header("Authorization",token)
                .contentType(ContentType.JSON)
                .contentType("application/json")
                .accept(ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("https://api.spotify.com/v1/users/313ezwhpfwgm7kspa45ceyqhgtu4/playlists");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void getPlaylistWithPlaylistId(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/playlists/6QYI9cKZOqPYLI2QIBQB3P");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void getListOfCurrentUsersPlaylist(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void getPlaylistItems(){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/playlists/6QYI9cKZOqPYLI2QIBQB3P/tracks");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
}
