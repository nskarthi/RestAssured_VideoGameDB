package config;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestConfig {

	public static RequestSpecification videoGame_requestSpec;
	public static RequestSpecification football_requestSpec;
	public static ResponseSpecification responseSpec;

	@BeforeClass
	public static void setup() {
		football_requestSpec = new RequestSpecBuilder()
				.setBaseUri("http://api.football-data.org")
				.setBasePath("/v2/")
				.addHeader("x-Response-Control", "minified")
				.addHeader("x-Auth-Token", "63de3e1a914141ccb4f6b8a6b5c3bdd6")
				.addFilter(new RequestLoggingFilter())
				.addFilter(new ResponseLoggingFilter())
				.build();

		videoGame_requestSpec = new RequestSpecBuilder()
				.setBaseUri("http://video-game-db.eu-west-2.elasticbeanstalk.com")
				.setBasePath("/app/")
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.addFilter(new RequestLoggingFilter())
				.addFilter(new ResponseLoggingFilter())
				.build();

		// RestAssured.requestSpecification = football_requestSpec;
		RestAssured.requestSpecification = videoGame_requestSpec;

		responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();

		RestAssured.responseSpecification = responseSpec;
	}
}
