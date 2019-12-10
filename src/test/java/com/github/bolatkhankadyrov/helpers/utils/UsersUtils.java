package com.github.bolatkhankadyrov.helpers.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bolatkhankadyrov.helpers.models.UserModel;
import com.github.bolatkhankadyrov.Constants;
import com.github.bolatkhankadyrov.BaseRestClient;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersUtils extends BaseRestClient {
    public static List<UserModel> getUsersByUsername(String username) throws IOException {
        Map<String, String> param = new HashMap<>();
        param.put("username", username);

        Response response = fetch(Constants.USERS_PATH, param);

        String listOsUsersByUsername = BaseRestClient.writeValueAsString(response.jsonPath().get());

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<UserModel>> typeReference = new TypeReference<List<UserModel>>() {
        };

        return objectMapper.readValue(listOsUsersByUsername, typeReference);
    }

    public static List<UserModel> ResponseToUsers(Response response) throws IOException {
        String listOfUsers = BaseRestClient.writeValueAsString(response.jsonPath().get());

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<UserModel>> typeReference = new TypeReference<List<UserModel>>() {
        };

        return objectMapper.readValue(listOfUsers, typeReference);
    }
}
