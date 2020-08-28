package VideoGameDB.config;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class VideoGameConfig {
	
	public static RequestSpecification videoGame_requestSpec;
	public static ResponseSpecification videoGame_responseSpec;
	
	@BeforeClass
	public static void setup() {
		// example: http://video-game-db.eu-west-2.elasticbeanstalk.com/app/videogames
		videoGame_requestSpec = new RequestSpecBuilder()
				.setBaseUri("http://video-game-db.eu-west-2.elasticbeanstalk.com")
				.setBasePath("/app/")
				.addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json")
				.build();

		videoGame_responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.build();

		RestAssured.requestSpecification = videoGame_requestSpec;
		RestAssured.responseSpecification = videoGame_responseSpec;
	}
}
