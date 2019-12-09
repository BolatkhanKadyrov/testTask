package com.github.bolatkhankadyrov.helpers;

import com.github.bolatkhankadyrov.utils.Constants;
import com.github.bolatkhankadyrov.utils.RestUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class Posts extends RestUtil {
    public Response getResponseByUserID(String ID) {
        Map<String, String> param = new HashMap<>();
        param.put("userId", ID);
        return fetch(Constants.POSTS_ENDPOINT, param);
    }
}
