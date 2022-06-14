package com.bridgelabz.restassuredapi.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestAssured_API_Automation {
    String token;
    static String userId, playlistId, trackId;

    @BeforeTest
    public void getToken(){
        token = "Bearer BQDo6kwea5BYksxxw4F7M6RIRLmvyXdSGsIz3jWoddOFaHuOQl4N0HO5cTdUhRK_ftlqrIu-7HZaRCZ6bbUlAA9Bm57JCC1cLx71pRLCl2RTVuxj8bvGaynNnS3V4gdWucuV8ZDt16ZOzOPpFZZa2RSWIVUfK_h11d2iS2udURjwGCSwig7BYUgjkn20u_yGhhUqsgCVw4gqemwd5-CrnBoNcTDhStprnO8pij-MUfyXMpSpJXUNr1LAmP7ZjbZvi6XbTtVu72hZN8t7ur__s8w";
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
                .put("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
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
                .delete("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        response.prettyPrint();
    }

    @Test
    public void searchItem(){
        System.out.println("--------------------Search item--------------------");
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .header("Authorization",token)
                .pathParam("q", "RRR")
                .pathParam("type", "track")
                .when()
                .get("https://api.spotify.com/v1/search?q={q}&type={type}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void getAvailableMarkets(){
        System.out.println("--------------------get Available Markets--------------------");
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/markets");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void addItemsToPlaylist(){
        System.out.println("--------------------add Items To Playlist--------------------");
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .header("Authorization",token)
                .pathParam("position", "1")
                .pathParam("uris", "spotify:track:6FQQiTpYnfc5803p84bQp1")
                .when()
                .put("https://api.spotify.com/v1/playlists/1Yh4R3U45d34G16C2o3H6p/tracks?position={position}&uris={uris}");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void getTracks(){
        System.out.println("--------------------get tracks--------------------");
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("ContentType", "application/json")
                .header("Authorization",token)
                .when()
                .get("https://api.spotify.com/v1/tracks/6FQQiTpYnfc5803p84bQp1");
        response.prettyPrint();
        trackId = response.path("id");
        System.out.println("Track ID: " + trackId);
        response.then().assertThat().statusCode(200);
    }
}
