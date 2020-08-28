package VideoGameDB.RestAssured_01;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestConfig {
	
	@BeforeClass
	public static void setup() {
		// example: http://video-game-db.eu-west-2.elasticbeanstalk.com/app/videogames
		RestAssured.baseURI = "http://video-game-db.eu-west-2.elasticbeanstalk.com";
		RestAssured.basePath = "/app/";

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.build();

		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();

		RestAssured.requestSpecification = requestSpecification;
		RestAssured.responseSpecification = responseSpecification;
	}
}