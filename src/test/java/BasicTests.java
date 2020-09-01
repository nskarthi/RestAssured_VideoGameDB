import org.junit.Test;

import config.TestConfig;
import config.Endpoints;

import static io.restassured.RestAssured.*;

public class BasicTests extends TestConfig {

	@Test
	public void sample() {
		given()
			.log().all().
		when().get(Endpoints.ALL_VIDEO_GAMES).
		then()
			.log().all();
	}
	
	@Test
	public void myFirstTestWithEndpoint() {
		get(Endpoints.ALL_VIDEO_GAMES).
		then().log().all();
	}
}
