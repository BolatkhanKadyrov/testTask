package com.github.bolatkhankadyrov.tests.users;

import com.github.bolatkhankadyrov.helpers.models.UserModel;
import com.github.bolatkhankadyrov.matchers.UsersMatcher;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.github.bolatkhankadyrov.Constants.*;
import static com.github.bolatkhankadyrov.helpers.utils.UsersUtils.responseToUsers;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UsersSpec {
    private static RequestSpecification spec;

    @BeforeClass
    public static void initSpec() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    private UserModel expectedUser() {
        return new UserModel()
                .setId(3L)
                .setName("Clementine Bauch")
                .setUsername("Samantha");
    }

    @Test
    public void checkUser() throws IOException {
        Response response = given()
                .spec(spec)
                .queryParam(USERNAME, "Samantha")
                .when()
                .get(USERS_PATH)
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(1))
                .extract()
                .response();

        List<UserModel> actualUsers = responseToUsers(response);

        UsersMatcher.correctUser(expectedUser(), actualUsers.get(0));
    }
}
