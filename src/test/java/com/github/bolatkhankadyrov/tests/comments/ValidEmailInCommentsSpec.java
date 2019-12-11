package com.github.bolatkhankadyrov.tests.comments;

import com.github.bolatkhankadyrov.helpers.models.CommentModel;
import com.github.bolatkhankadyrov.helpers.models.PostModel;
import com.github.bolatkhankadyrov.helpers.models.UserModel;
import com.github.bolatkhankadyrov.helpers.utils.PostsUtils;
import com.github.bolatkhankadyrov.matchers.CommentsMatcher;
import com.github.bolatkhankadyrov.matchers.PostsMatcher;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.github.bolatkhankadyrov.Constants.BASE_URL;
import static com.github.bolatkhankadyrov.Constants.COMMENTS_PATH;
import static com.github.bolatkhankadyrov.helpers.utils.CommentsUtils.responseToComments;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ValidEmailInCommentsSpec {
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
    public void checkEmailsOfCommentsInUsersPosts() throws IOException {
        // given
        List<PostModel> actualPosts = PostsUtils.getPostsByUserId(expectedUser().getId(), spec);

        // when
        PostsMatcher.postsHasCorrectUserId(actualPosts, expectedUser().getId());

        Response responseComments = given()
                .spec(spec)
                .when()
                .get(COMMENTS_PATH)
                .then()
                .statusCode(200)
                .body("$.size()", equalTo(500))
                .extract()
                .response();

        List<CommentModel> actualComments = responseToComments(responseComments);

        // then
        CommentsMatcher.commentsInPostsHasCorrectEmail(actualComments, actualPosts);
    }
}
