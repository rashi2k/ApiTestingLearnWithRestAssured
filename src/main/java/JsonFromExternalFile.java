import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.ReusableMathods;

public class JsonFromExternalFile {
	
	@Test
	public void addBook() throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";
		
		//given
		//when
		//then
		
		String response = given().header("Content-Type","application/json").body(generateStringFromResource("D:\\Quality Assurance\\Workspacess\\Leaning\\API testing\\ApiTestingLearnWithRestAssured\\src\\main\\java\\json\\addBook.json")).
		when().post("/Library/Addbook.php").then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath jsonPath = ReusableMathods.rowToJson(response);
		String id = jsonPath.get("ID");
		System.out.println("id " + id);
	}
	
	public static String generateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
