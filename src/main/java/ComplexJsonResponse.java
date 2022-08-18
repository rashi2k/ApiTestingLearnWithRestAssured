import java.util.List;

import files.Payloads;import groovy.transform.stc.FirstParam.FirstGenericType;
import io.restassured.path.json.JsonPath;

public class ComplexJsonResponse {
	public static void main(String[] args) {

//
//		6. Verify if Sum of all Course prices matches with Purchase Amount
		
		JsonPath jsonPath = new JsonPath(Payloads.coursePrices());

		// Print No of courses returned by API
		int coursesCount = jsonPath.getInt("courses.size()");
		System.out.println("course count : " + coursesCount);
		
		// Print Purchase Amount 
		int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
		System.out.println("purchaseAmount: " + purchaseAmount);
		
		 //Print Title of the first course
		
		String firstCourseTitle = jsonPath.get("courses[0].title");
		System.out.println("first course title : " + firstCourseTitle);
		
		//Print All course titles and their respective Prices
		
		for(int i = 0; i< coursesCount; i++) {
			String title = jsonPath.get("courses["+i+"].title");
			int price = jsonPath.get("courses["+i+"].price");
			
			System.out.println("title : " + title + " and pirce : " + price);
		}
		
		 //Print no of copies sold by RPA Course
		
		for(int i = 0; i< coursesCount; i++) {
			String title = jsonPath.get("courses["+i+"].title");
			if(title.equals("RPA")) {
				int copies = jsonPath.get("courses["+i+"].copies");
				System.out.println("title : " + title + " and copies : " + copies);
			}
		}
	}

}
