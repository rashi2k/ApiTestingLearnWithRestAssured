import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payloads;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumCourses() {
		int sum = 0;

		JsonPath js = new JsonPath(Payloads.coursePrices());
		int count = js.getInt("courses.size()");
		int purchseAmount = js.getInt("dashboard.purchaseAmount");
		for (int i = 0; i < count; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			int total = price * copies;
			sum = sum + total;
			System.out.println(total);
		}
		System.out.println("purchseAmount : " + purchseAmount);
		System.out.println("sum of all the courses : " + sum);
		
		Assert.assertEquals(sum, purchseAmount);
	}
}
