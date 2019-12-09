package com.github.bolatkhankadyrov.helpers;

import com.github.bolatkhankadyrov.utils.Constants;
import com.github.bolatkhankadyrov.utils.RestUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Comments extends RestUtil {
    private Response response;

    public Response getResponseByPostID(String ID) {
        Map<String, String> param = new HashMap<>();
        param.put("postId", ID);
        response = fetch(Constants.COMMENTS_ENDPOINT, param);
        return response;
    }

    public List<String> getListOfValuesOfEmailsInResponse() {
        return response.jsonPath().getList("email");
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
