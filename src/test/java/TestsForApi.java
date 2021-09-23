import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestsForApi {
    RequestSpecification request = given()
            .baseUri("https://jsonplaceholder.typicode.com/posts")
            .header("Language", "en");

    @Test
    public void getAllPosts() {
        request.when()
                .get("")
                .then()
                .assertThat()
                .statusCode(200)
                .body("", Matchers.hasSize(100));
    }

    @Test
    public void getUserPostsByCorrectUserId() {
        request.when()
                .get("?userId=1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("userId", Matchers.everyItem(equalTo(1)));

    }

    @Test
    public void getUserPostsByZeroUserId() {
        request.when()
                .get("?userId=0")
                .then()
                .assertThat()
                .statusCode(200)
                .body("isEmpty()", Matchers.equalTo(true));
    }

    @Test
    public void getUserPostsByEmptyUserId() {
        request.when()
                .get("?userId=")
                .then()
                .assertThat()
                .statusCode(200)
                .body("isEmpty()", Matchers.equalTo(true));
    }

    @Test
    public void getUserPostsByInvalidUserId() {
        request.when()
                .get("?userId=xxxxxx")
                .then()
                .assertThat()
                .statusCode(200)
                .body("isEmpty()", Matchers.equalTo(true));

        request.when()
                .get("?userId=fdfdsl5sdfg")
                .then()
                .assertThat()
                .statusCode(200)
                .body("isEmpty()", Matchers.equalTo(true));
    }

    @Test
    public void getPostByCorrectId() {
        request.when()
                    .get("1")
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body("id", Matchers.equalTo(1));

        request.when()
                .get("50")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo(50));

        request.when()
                .get("100")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", Matchers.equalTo(100));
    }

    @Test
    public void getPostByZeroId() {
        request.when()
                .get("0")
                .then()
                .assertThat()
                .statusCode(200)
                .body("isEmpty()", Matchers.equalTo(true));
    }

    @Test
    public void getPostByInvalidId() {
        request.when()
                .get("xxxxxx")
                .then()
                .assertThat()
                .statusCode(404)
                .body("isEmpty()", Matchers.equalTo(true));

    }
}
