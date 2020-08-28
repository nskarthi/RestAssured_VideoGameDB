package VideoGameDB.RestAssured_01;

import org.junit.Ignore;
import org.junit.Test;

import VideoGameDB.config.VideoGameConfig;
import VideoGameDB.config.VideoGamesEndpoints;
import static io.restassured.RestAssured.*;

public class VideoGameDBTests extends VideoGameConfig {

	@Ignore
	@Test
	public void getAllGames()  {
		given().
		when().
			get(VideoGamesEndpoints.ALL_VIDEO_GAMES).
		then();
	}

	@Test
	public void createNewGameByJSON()  {
		String gameBodyJson = "{\n" + 
					"\"id\": 100,\n" +
					"\"name\": \"Super Mario 4\",\n" +
					"\"releaseDate\": \"2019-10-01\",\n" + 
					"\"reviewScore\": 89,\n" + 
					"\"category\": \"Tricky\",\n" + 
					"\"rating\": \"Universal\"\n" + 
				"}";
		given().
			body(gameBodyJson).
		when().
			post(VideoGamesEndpoints.ALL_VIDEO_GAMES).
		then();
	}
}
