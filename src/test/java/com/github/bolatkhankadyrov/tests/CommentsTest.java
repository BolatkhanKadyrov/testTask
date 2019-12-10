package com.github.bolatkhankadyrov.tests;

import com.github.bolatkhankadyrov.helpers.models.CommentModel;
import com.github.bolatkhankadyrov.helpers.models.PostModel;
import com.github.bolatkhankadyrov.helpers.models.UserModel;
import com.github.bolatkhankadyrov.matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.github.bolatkhankadyrov.Constants.BASE_URL;
import static com.github.bolatkhankadyrov.Constants.USERS_PATH;
import static com.github.bolatkhankadyrov.Constants.POSTS_PATH;
import static com.github.bolatkhankadyrov.Constants.COMMENTS_PATH;
import static com.github.bolatkhankadyrov.helpers.utils.CommentsUtils.ResponseToComments;
import static com.github.bolatkhankadyrov.helpers.utils.PostsUtils.ResponseToPosts;
import static com.github.bolatkhankadyrov.helpers.utils.UsersUtils.ResponseToUsers;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CommentsTest {
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
                .queryParam("username", "Samantha")
                .when()
                .get(USERS_PATH)
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(1))
                .extract()
                .response();

        List<UserModel> actualUsers = ResponseToUsers(response);

        UsersMatcher matcher = new UsersMatcher();

        matcher.correctUser(expectedUser(), actualUsers.get(0));
    }

    @Test
    public void checkEmailsOfCommentsInUsersPosts() throws IOException {
        Response responsePosts = given()
                .spec(spec)
                .queryParam("userId", expectedUser().getId())
                .when()
                .get(POSTS_PATH)
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(10))
                .extract()
                .response();

        List<PostModel> actualPosts = ResponseToPosts(responsePosts);

        for (PostModel post : actualPosts) {
            if (!post.getUserId().equals(expectedUser().getId())) {
                System.out.println("Post with id:" + post.getId() + "has wrong userId");
                throw new AssertionError();
            }
        }

        Response responseComments = given()
                .spec(spec)
                .when()
                .get(COMMENTS_PATH)
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(500))
                .extract()
                .response();

        List<CommentModel> actualComments = ResponseToComments(responseComments);

        CommonMatcher emailMatcher = new CommonMatcher();

        List<Integer> postIDs = responsePosts.path("id");

        for (Integer postId : postIDs) {
            for (CommentModel comment : actualComments) {
                if (comment.getPostId() == postId && !emailMatcher.isValidEmail(comment.getEmail())) {
                    throw new AssertionError();
                }
            }
        }
    }
}
