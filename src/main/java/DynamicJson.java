import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payloads;
import files.ReusableMathods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) {

		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(Payloads.addBook(isbn, aisle)).when().post("/Library/Addbook.php").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		JsonPath jsonPath = ReusableMathods.rowToJson(response);
		String id = jsonPath.get("ID");
		System.out.println("book id" + id);
	}

	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		// array = collection of elements
		// multi dimentional array = collection of arrays
		return new Object[][] { { "adada", "11212" }, { "ddvd", "4544" }, { "tytyd", "4542" } };
	}
}
