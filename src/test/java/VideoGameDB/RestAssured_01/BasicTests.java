package VideoGameDB.RestAssured_01;

import org.junit.Ignore;
import org.junit.Test;

import VideoGameDB.config.VideoGameConfig;
import VideoGameDB.config.VideoGamesEndpoints;

import static io.restassured.RestAssured.*;
public class BasicTests extends VideoGameConfig {

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
		get(VideoGamesEndpoints.ALL_VIDEO_GAMES).
		then().log().all();
	}
}