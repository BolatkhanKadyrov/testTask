package com.github.bolatkhankadyrov.helpers;

import com.github.bolatkhankadyrov.utils.Constants;
import com.github.bolatkhankadyrov.utils.RestUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class UsersHelper extends RestUtil {
    public Response getResponseByUsername(String username) {
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        return fetch(Constants.USERS_ENDPOINT, param);
    }
}
