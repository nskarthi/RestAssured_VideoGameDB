package VideoGameDB.RestAssured_01;

import org.junit.Test;

import VideoGameDB.config.VideoGameConfig;
import VideoGameDB.config.VideoGamesEndpoints;
import static io.restassured.RestAssured.*;

public class VideoGameDBTests extends VideoGameConfig {

	@Test
	public void getAllGames()  {
		given().
		when()
			.get(VideoGamesEndpoints.ALL_VIDEO_GAMES).
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
		given()
			.body(gameBodyJson).
		when()
			.post(VideoGamesEndpoints.ALL_VIDEO_GAMES).
		then();
	}

	@Test
	public void createNewGameByXML()  {
		String gameBodyXML = "<videoGame category=\"Tricky\" rating=\"Universal\">" + 
					"<id>101</id>\n" + "<name>Super Mario 5</name>\n" + 
					"<releaseDate>2019-12-01T00:00:00Z</releaseDate>\n" + 
					"<reviewScore>90</reviewScore>\n" + "</videoGame>";
	given()
		.body(gameBodyXML)
		.header("Content-Type", "application/xml")
		.header("Accept", "application/xml").
	when()
		.post(VideoGamesEndpoints.ALL_VIDEO_GAMES).
	then();
	}

	@Test
	public void updateGameByJSON()  {
		String gameBodyJson = "{\n" + 
					"\"id\": 100,\n" +
					"\"name\": \"Super Mario 4\",\n" +
					"\"releaseDate\": \"2019-10-01\",\n" + 
					"\"reviewScore\": 91,\n" + 
					"\"category\": \"Tricky\",\n" + 
					"\"rating\": \"Universal\"\n" + 
				"}";
		given()
			.body(gameBodyJson).
		when()
			.put("videogames/100").
		then();
	}

	@Test
	public void deleteGame()  {
		given().
		when()
			.delete("videogames/100").
		then();
		
		given().
		when()
			.delete("videogames/101").
		then();
	}
	
	@Test
	public void getSingleGame() {
		given()
			.pathParam("videoGameId", 1).
		when()
			.get(VideoGamesEndpoints.SINGLE_VIDEO_GAME).
		then();
		}
}
