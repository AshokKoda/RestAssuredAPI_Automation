package com.bridgelabz.restassuredapi.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestAssured_API_Automation {
    String token;
    static String userId, playlistId;

    @BeforeTest
    public void getToken(){
        token = "Bearer BQChEO6do5jLhiHEYuPGt4rnvrjZ7_v1U4GQ3yX4P8sQouSRxshBXYvP0yNZM6kOlpSfXrU7OLE8l2sx-StZHFhZfl4OzJxC4Z0JuQdFM4EEpBC5JZfQr65OlV6aCxMsViP5ZTe7s3QoJfv2QKZmdab179oTzCTnV0lpclSCwQcBpyesb-7yRheEiDIs7fNTmpy3TCEEwKxvhlAyPJfWudfHAVyeB5VWZPptDXcpUikVSxlpl4Urg9AXlmezOWQclFH-y7jda4VyHGZyHF116Ro";
    }

    @Test(priority = 0)
    public void getCurrentUserProfile(){
        System.out.println("--------------------Get Current UserProfile--------------------");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/me");
        response.prettyPrint();
        userId = response.path("id");
        System.out.println("UserID: " + userId);

        response.then().assertThat().statusCode(200);
        Assert.assertEquals("313ezwhpfwgm7kspa45ceyqhgtu4", response.jsonPath().getString("id"));
    }

    @Test(priority = 1)
    public void getUserProfile(){
        System.out.println("--------------------Get UserProfile with userId--------------------");
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/users/"+userId+"/");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test(priority = 2)
    public void newCreatePlaylist(){
        System.out.println("--------------------Create new playlist--------------------");
        System.out.println("UserID: " + userId);
        String requestBody = "{\"name\":\"Harish Playlist\",\n\"description\":\"Telugu playlist description\",\n\"public\":\"false\"}";
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .header("Authorization",token)
                .body(requestBody)
                .when()
                .post("https://api.spotify.com/v1/users/"+userId+"/playlists");
        response.prettyPrint();
        playlistId = response.path("id");
        System.out.println("Playlist ID: " + playlistId);

        response.then().assertThat().statusCode(201);
        Assert.assertEquals(201, response.getStatusCode());
    }

    @Test(priority = 3)
    public void playlistGetWithPlaylistId(){
        System.out.println("--------------------Get Playlist--------------------");
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/playlists/"+playlistId+"/");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test(priority = 4)
    public void getUserPlaylist(){
        System.out.println("--------------------Get User Playlist--------------------");
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/users/"+userId+"/playlists");
        response.prettyPrint();
        String firstPlaylistId = response.path("items[0].id");
        String ownerName = response.path("items[0].owner.display_name");
        System.out.println("First playlist ID: " + firstPlaylistId);
        System.out.println("Owner name: " + ownerName);

        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test(priority = 5)
    public void updatePlaylist(){
        System.out.println("--------------------Update Playlist--------------------");
        String requestBody = "{\"range_start\":1,\"insert_before\":1,\"range_length\":1}";
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .header("Authorization",token)
                .body(requestBody)
                .when()
                .put("https://api.spotify.com/v1/playlists/209i7U7zf66jjO595mkqhD/tracks");
        response.prettyPrint();
    }

    @Test(priority = 6)
    public void removePlaylistItems(){
        System.out.println("--------------------Remove Playlist items--------------------");
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .header("Authorization",token)
                .when()
                .delete("https://api.spotify.com/v1/playlists/1Yh4R3U45d34G16C2o3H6p/tracks");
        response.prettyPrint();
    }
}
