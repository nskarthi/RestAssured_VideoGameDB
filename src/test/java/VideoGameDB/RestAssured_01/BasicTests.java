package VideoGameDB.RestAssured_01;

import org.junit.Test;
import static io.restassured.RestAssured.*;
public class BasicTests extends TestConfig {
	@Test
	public void sample() {
		given()
			.log().all().
		when().get("videogames").
		then()
			.log().all();
	}
}