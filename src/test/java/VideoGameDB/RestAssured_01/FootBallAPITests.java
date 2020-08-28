import config.footballAPIConfig

public class FootballAPITests extends FootballAPIConfig{
  @Test
  public void getDetailsOfOneArea() {
    given()
      .queryParam("areas", 2255).
    when()
      .get("areas").
    then();
  }
}
