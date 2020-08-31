package VideoGameDB.RestAssured_01;

import org.junit.Test;

import VideoGameDB.config.Endpoints;
import VideoGameDB.config.TestConfig;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GPathJSONTests extends TestConfig {

	@Test
	public void extractMapOfElementsWithFind() {
		Response response = get(Endpoints.COMPETITION_DETAILS);
		Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'Manchester United FC' }");
		System.out.println("Map of Team Data: " + allTeamDataForSingleTeam);
	}
	
	@Test
	public void extractSingleValueWithFind() {
		Response response = get(Endpoints.TEAM_DETAILS);
		String certainPlayer = response.path("squad.find { it.id == 7779}.name");
		System.out.println("Name of the player: " + certainPlayer);
	}
	
	@Test
	public void extractListOfValuesWithFindAll() {
		Response response = get(Endpoints.TEAM_DETAILS);
		List<String> playerNames = response.path("squad.findAll { it.position == 'Goalkeeper'}.name");
		System.out.println(playerNames);
	}
	
	@Test
	public void extractSingleValueWithHighestNumber() {
		Response response = get(Endpoints.TEAM_DETAILS);
		String playerName = response.path("squad.max { it.id }.name");
		System.out.println("Player with highest id: " + playerName);
	}
	
	@Test
	public void extractMultipleValuesAndSumThem() {
		Response response = get(Endpoints.TEAM_DETAILS);
		int sumOfIds = response.path("squad.collect { it.id }.sum()");
		System.out.println("Sum of all Ids: " + sumOfIds);
	}
	
	@Test
	public void extractMapOfObjectWithFindAndFindAll() {
		Response response = get(Endpoints.TEAM_DETAILS);
		Map<String, ?> playerOfCertainPosition = response.path("squad.findAll { it.position == 'Defender' }."
				+ "find { it.nationality == 'Greece' }");
		System.out.println("Details of players: " + playerOfCertainPosition);
	}

	@Test
	public void extractMapOfObjectWithFindAndFindAllWithParameters() {
		String position = "Defender";
		String nationality = "Greece";
		
		Response response = get(Endpoints.TEAM_DETAILS);
		Map<String, ?> playerOfCertainPosition = response.path("squad.findAll { it.position == '%s' }."
				+ "find { it.nationality == '%s' }", position, nationality);
		System.out.println("Details of players: " + playerOfCertainPosition);
	}
	
	@Test
	public void extractMultiplePlayers() {
		String position = "Midfielder";
		String nationality = "England";

		Response response = get(Endpoints.TEAM_DETAILS);

		ArrayList<Map<String, ?>> allPlayersCertainNation = response.path("squad.findAll { it.position == '%s' }."
				+ "findAll { it.nationality == '%s' }", position, nationality);
		System.out.println("All Players: " + allPlayersCertainNation);
	}
	
	@Test
	public void extractNamesOfPlayersFromCertianCountryAndInCertainPosition() {
		String position = "Midfielder";
		String nationality = "England";

		Response response = get(Endpoints.TEAM_DETAILS);
		List<String> playerNames = response.path("squad.findAll { it.position == '%s' }."
				+ "findAll { it.nationality == '%s' }.name", position, nationality);
		System.out.println("Player Names: " + playerNames);

	}
	
}
