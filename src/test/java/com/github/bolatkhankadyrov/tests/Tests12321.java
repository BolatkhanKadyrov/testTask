package com.github.bolatkhankadyrov.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bolatkhankadyrov.helpers.UsersHelper;
import com.github.bolatkhankadyrov.helpers.models.UserModel;
import com.github.bolatkhankadyrov.utils.RestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Tests12321 {
    private UsersHelper users;

    @Test
    public void testValidateFormatOfEmailsInCommentsSectionForAllPostsOfAUser() throws IOException {
        String usr = RestUtil.writeValueAsString(users.getResponseByUsername("Samantha").jsonPath().get());
        System.out.println(usr);
        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<UserModel>> typeReference = new TypeReference<List<UserModel>>(){};

        List<UserModel> user = objectMapper.readValue(usr, typeReference);

        Assert.assertEquals(user.get(0).getId(), 3L);


    }

    @Before
    public void before() {
        users = new UsersHelper();
    }
}
