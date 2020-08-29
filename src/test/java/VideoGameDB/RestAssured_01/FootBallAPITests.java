package VideoGameDB.RestAssured_01;

import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import static io.restassured.RestAssured.*;

import VideoGameDB.config.FootballApiConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class FootballAPITests extends FootballApiConfig {

	@Ignore
	@Test
	public void getDetailsOfOneArea() {
		given()
			.queryParam("areas", 2255).
		when()
			.get("areas").
		then();
	}

	@Ignore
	@Test
	public void getDateFounded() {
		given().
		when()
			.get("teams/57").
		then()
			.body("founded", equalTo(1886));
	}

	@Test
	public void getFirstTeamName() {
		given().
		when()
			.get("competitions/2021/teams").
		then()
			.body("teams[2].name", equalTo("Chelsea FC"));
	}

	@Test
	public void getAllTeamData() {
		String responseBody = get("teams/57").asString();
		System.out.println(responseBody);
	}
	
	@Test
	public void getAllTeamData_DoCheckFirst() {
		Response response = 
				given().
				when()
					.get("teams/57").
				then()
					.contentType(ContentType.JSON)
					.extract().response();
							
		String jsonResponseAsString = response.asString();
		System.out.println(jsonResponseAsString);
	}

	@Test
	public void extractHeaders() {
		Response response = 
				given().
				when()
					.get("teams/57").
				then()
					.contentType(ContentType.JSON)
					.extract().response();
							
		Headers headers = response.getHeaders();
		for(Header header:headers) {
			System.out.println(header.getName() + " : " + header.getValue());
		}
		
		String contentType = response.getHeader("Content-Type");
		System.out.println();
		System.out.println("Content Type: " + contentType);
	}

	@Test
	public void extractFirstTeamName() {
		String firstTeamName = get("competitions/2021/teams").jsonPath().getString("teams[0].name");
		System.out.println("Content Type: " + firstTeamName);
	}
	
	@Test
	public void extractAllTeamNames() {
		Response response = 
				given().
				when()
					.get("competitions/2021/teams").
				then()
					.contentType(ContentType.JSON)
					.extract().response();

		List<String> teamNames = response.path("teams.name");
		for(String teamName : teamNames)
			System.out.println(teamName);
	}
	
}
