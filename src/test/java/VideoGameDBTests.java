import org.junit.Test;

import VideoGameDB.RestAssured_01.VideoGamePOJO;

import static org.hamcrest.Matchers.lessThan;

import config.TestConfig;
import config.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class VideoGameDBTests extends TestConfig {

	@Test
	public void getAllGames()  {
		given().
		when()
			.get(Endpoints.ALL_VIDEO_GAMES).
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
			.post(Endpoints.ALL_VIDEO_GAMES).
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
		.post(Endpoints.ALL_VIDEO_GAMES).
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
			.get(Endpoints.SINGLE_VIDEO_GAME).
		then();
		}
	
	@Test
	public void testVideoGameSerializationByJSON() {
		VideoGamePOJO videoGame = new VideoGamePOJO("81", "2005-11-01", "Macho Man", "CBE", "104", "Killer");
		given()
			.body(videoGame).
		when()
			.post(Endpoints.ALL_VIDEO_GAMES).
		then();
	}
	
	@Test
	public void convertJSONtoPOJO() {
		Response response = 
				given()
					.pathParam("videoGameId", 103).
				when()
					.get(Endpoints.SINGLE_VIDEO_GAME);
		
		VideoGamePOJO videoGamePojo = response.body().as(VideoGamePOJO.class);
		System.out.println(videoGamePojo.toString());
	}
	
	@Test
	public void testVideoGameSchemaXML() {
		given()
			.pathParam("videoGameId", 102)
			.header("Content-Type", "application/xml")
			.header("Accept", "application/xml").
		when()
			.get(Endpoints.SINGLE_VIDEO_GAME).
		then()
			.body(matchesXsdInClasspath("VideoGameXSD.xsd"));
	}

	@Test
	public void testVideoGameSchemaJSON() {
		given()
			.pathParam("videoGameId", 103).
		when()
			.get(Endpoints.SINGLE_VIDEO_GAME).
		then()
			.body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
	}
	
	@Test
	public void captureResponseTime() {
		long responseTime = get(Endpoints.ALL_VIDEO_GAMES).time();
		System.out.println("Response Time in MS: " + responseTime);
	}
	
	@Test
	public void assertOnResponseTime() {
		when().
			get(Endpoints.ALL_VIDEO_GAMES).
		then()
			.time(lessThan(1600L));

	}
	
}
