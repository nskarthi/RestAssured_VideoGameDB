package VideoGameDB.RestAssured_01;

import org.junit.Ignore;
import org.junit.Test;

import VideoGameDB.config.TestConfig;
import VideoGameDB.config.Endpoints;

import static io.restassured.RestAssured.*;

public class BasicTests extends TestConfig {

	@Ignore
	@Test
	public void sample() {
		given()
			.log().all().
		when().get("videogames").
		then()
			.log().all();
	}
	
	@Test
	public void myFirstTestWithEndpoint() {
		get(Endpoints.ALL_VIDEO_GAMES).
		then().log().all();
	}
}
