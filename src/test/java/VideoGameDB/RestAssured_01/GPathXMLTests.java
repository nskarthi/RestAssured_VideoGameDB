package VideoGameDB.RestAssured_01;

import org.junit.Test;

import VideoGameDB.config.Endpoints;
import VideoGameDB.config.TestConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GPathXMLTests extends TestConfig {

	@Test
	public void getFirstGameInList() {
		Response response = get(Endpoints.ALL_VIDEO_GAMES);
		String name = response.path("videoGames.videoGame.name[0]");
		System.out.println(name);
	}

	@Test
	public void getAttributeName() {
		Response response = get(Endpoints.ALL_VIDEO_GAMES);
		String category = response.path("videoGames.videoGame[0].@category");
		System.out.println(category);
	}

	@Test
	public void getListOfXmlNodes() {
		String responseAsString = get(Endpoints.ALL_VIDEO_GAMES).asString();
		
		List<Node> allResults = XmlPath.from(responseAsString).get(
				"videoGames.videoGame.findAll { element -> return element } ");
		System.out.println(allResults.get(2).get("name").toString());
	}

	@Test
	public void getListOfXmlNodesByFindAllOnAttribute() {
		String responseAsString = get(Endpoints.ALL_VIDEO_GAMES).asString();
		
		List<Node> allDrivingGames = XmlPath.from(responseAsString).get(
				"videoGames.videoGame.findAll { videoGame -> def category = videoGame.@category; category == 'Driving' }");
		System.out.println(allDrivingGames.get(0).get("name").toString());
	}
	
	@Test
	public void getSingleNode() {
		String responseAsString = get(Endpoints.ALL_VIDEO_GAMES).asString();
		
		Node videoGame = XmlPath.from(responseAsString).get(
				"videoGame.videoGame.find { a -> def name = a.name; name == 'Tetris' }");
		System.out.println(videoGame.get("name"));
	}
	
	@Test
	public void getListOfXmlNodes1() {
		String responseAsString = get(Endpoints.ALL_VIDEO_GAMES).asString();
		
		List<String> allResults = XmlPath.from(responseAsString).get(
				"videoGames.videoGame.findAll { element -> return element.name } ");
		System.out.println(allResults.toString());
	}
	
}

