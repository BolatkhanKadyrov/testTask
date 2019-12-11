package com.github.bolatkhankadyrov.helpers.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bolatkhankadyrov.helpers.models.PostModel;
import com.github.bolatkhankadyrov.Constants;
import com.github.bolatkhankadyrov.BaseRestClient;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.bolatkhankadyrov.Constants.POSTS_PATH;
import static com.github.bolatkhankadyrov.Constants.USER_ID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostsUtils extends BaseRestClient {
    public static List<PostModel> getPostsByUserId0(String userId) throws IOException {
        Map<String, String> param = new HashMap<>();
        param.put(USER_ID, userId);

        Response response = fetch(Constants.POSTS_PATH, param);

        String listOsPostsByUserId = BaseRestClient.writeValueAsString(response.jsonPath().get());

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<PostModel>> typeReference = new TypeReference<List<PostModel>>() {
        };

        return objectMapper.readValue(listOsPostsByUserId, typeReference);
    }

    private static Response getByUserId(String id) {
        Map<String, String> param = new HashMap<>();
        param.put(USER_ID, id);
        return fetch(Constants.POSTS_PATH, param);
    }

    private static List<PostModel> responseToPosts(Response response) throws IOException {
        String listOfPosts = BaseRestClient.writeValueAsString(response.jsonPath().get());

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<PostModel>> typeReference = new TypeReference<List<PostModel>>() {
        };

        return objectMapper.readValue(listOfPosts, typeReference);
    }

    public static List<PostModel> getPostsByUserId(long userId, RequestSpecification spec) throws IOException {
        Response responsePosts = given()
                .spec(spec)
                .queryParam(USER_ID, userId)
                .when()
                .get(POSTS_PATH)
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(10))
                .extract()
                .response();

        return responseToPosts(responsePosts);
    }
}
