import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

public class Jira_PathParamAndSession {
	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8080";
		RestAssured.useRelaxedHTTPSValidation(); // by pass https validation
		
		// Login scenario
		SessionFilter session = new SessionFilter();
		given().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"username\": \"rashi2k\",\r\n" + "    \"password\": \"coderush@123\"\r\n" + "}")
				.filter(session).log().all().when().post("/rest/auth/1/session").then().log().all().assertThat()
				.statusCode(200);

		// Add comment scenario
		String message = "New comment from new code";
		String responseAddComments = given().pathParam("issueKey", "10000").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \""+ message +"\",\r\n" + "    \"visibility\": {\r\n"
						+ "        \"type\": \"role\",\r\n" + "        \"value\": \"Administrators\"\r\n" + "    }\r\n"
						+ "}")
				.filter(session).when().post("/rest/api/2/issue/{issueKey}/comment").then().log().all().assertThat()
				.statusCode(201).extract().response().asString();

		JsonPath jsC = new JsonPath(responseAddComments);
		String commentId = jsC.get("id").toString();
		// Add attachment
		given().pathParam("issueKey", "10000").header("Content-Type", "multipart/form-data")
				.header("X-Atlassian-Token", "no-check").multiPart("file", new File("test.jpg")).log().all()
				.filter(session).when().post("/rest/api/2/issue/{issueKey}/attachments").then().log().all().assertThat()
				.statusCode(200);

		// Get an issue
		String getCommentsresponse = given().pathParam("issueKey", "10000").queryParam("fields", "comment")
				.filter(session).log().all().when().get("/rest/api/2/issue/{issueKey}").then().log().all().extract()
				.response().asString();

		
		// compare comments add above is in the get issue response
		JsonPath js = new JsonPath(getCommentsresponse);
		int commentsCount = js.get("fields.comment.comments.size()");
		for (int i = 0; i < commentsCount; i++) {
			System.out.println(js.get("fields.comment.comments["+i+"].id"));
			String cIdFromIssueFromResponse = js.get("fields.comment.comments["+i+"].id").toString();
			if(cIdFromIssueFromResponse.equalsIgnoreCase(commentId)) {
				String messageFromIssueResponse = js.get("fields.comment.comments["+i+"].body").toString();
				System.out.println(messageFromIssueResponse);
				Assert.assertEquals(messageFromIssueResponse, message);
			}
		}

	}
}
