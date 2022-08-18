import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payloads;
import files.ReusableMathods;

public class Baics {

	public static void main(String[] args) {
		// validate if app country api is working

		// given
		// when
		// Then

		RestAssured.baseURI = "http://localhost:8080";

		// add country
		String response = given().log().all().headers("Content-Type", "application/json").body(Payloads.addCountry())
				.when().post("/countries").then().assertThat().statusCode(201).body("countryName", equalTo("Italy"))
				.header("Transfer-Encoding", "chunked").extract().response().asString();

		System.out.println(response);
		JsonPath jsonPath = new JsonPath(response);
		String countryId = jsonPath.getString("id");
		System.out.println(countryId);

		// update country

		String newCounryName = "England";
		String newCountryCapital = "London";

		given().log().all().pathParam("id", countryId).header("Content-Type", "application/json").body(Payloads.updateCountry(newCounryName, newCountryCapital))
		.when().put("/countries/{id}")
		.then().assertThat().log().all().statusCode(200).body("countryName", equalTo(newCounryName));
		
		// get country
		
		String getCountryResponse = given().log().all().pathParam("id", countryId)
		.when().get("/countries/{id}")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath jsonPath2 = ReusableMathods.rowToJson(getCountryResponse);
		String actualCountryName = jsonPath2.getString("countryName");
		
		System.out.println(actualCountryName);
		Assert.assertEquals(actualCountryName, newCounryName);

	}

}
