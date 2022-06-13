package com.bridgelabz.jsonserver.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class JsonServerDbTest {

    @Test
    public void postRequestForPosts(){
        System.out.println("--------------------Create new Post--------------------");
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body("{\"id\":\"6\",\n\"title\":\"Friday\",\n\"author\":\"Week\"}")
                .when()
                .post("http://localhost:3000/posts/");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void getRequestForPosts(){
        System.out.println("--------------------Get Posts--------------------");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:3000/posts");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void updateRequestForPosts(){
        System.out.println("--------------------Update Post--------------------");
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body("{\"id\":\"1\",\n\"title\":\"Sunday\",\n\"author\":\"Week\"}")
                .when()
                .put("http://localhost:3000/posts/1");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void deleteRequestForPosts(){
        System.out.println("--------------------Delete Post--------------------");
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .when()
                .delete("http://localhost:3000/posts/5");
        System.out.println("Deleted successfully.");
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void getRequestForPostsWithId(){
        System.out.println("--------------------Get Posts using id--------------------");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", "2")
                .when()
                .get("http://localhost:3000/posts?id={id}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void createNewComment(){
        System.out.println("--------------------Create new comment--------------------");
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body("{\"id\":\"3\",\n\"body\":\"some comment is here\",\n\"postId\":\"2\"}")
                .when()
                .post("http://localhost:3000/comments/");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void getRequestForComments(){
        System.out.println("--------------------Get Comments--------------------");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:3000/comments");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void getCommentsUsingPostId(){
        System.out.println("--------------------Get Comments using postId--------------------");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("postId", "2")
                .when()
                .get("http://localhost:3000/comments?postId={postId}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void updateRequestForComments(){
        System.out.println("--------------------Update comments--------------------");
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body("{\"id\":\"2\",\n\"body\":\"some comment is here\",\n\"postId\":\"2\"}")
                .when()
                .put("http://localhost:3000/comments/2");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void deleteRequestForComment(){
        System.out.println("--------------------Delete comment--------------------");
        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .when()
                .delete("http://localhost:3000/comments/2");
        System.out.println("Deleted successfully.");
        response.then().assertThat().statusCode(200);
    }
}
