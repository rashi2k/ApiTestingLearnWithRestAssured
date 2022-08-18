package files;

public class Payloads {

	public static String addCountry() {
		return "{\r\n" + "    \"countryName\" : \"Italy\",\r\n" + "    \"capital\": \"Rome\"\r\n" + "}";
	}

	public static String updateCountry(String newCounryName, String newCountryCapital) {
		// TODO Auto-generated method stub
		return "{\r\n" + "    \"countryName\" : \"" + newCounryName + "\",\r\n" + "    \"capital\": \""
				+ newCountryCapital + "\"\r\n" + "}";
	}
	
	public static String coursePrices() {
		return "{\r\n" + 
				"\r\n" + 
				"\"dashboard\": {\r\n" + 
				"\r\n" + 
				"\"purchaseAmount\": 910,\r\n" + 
				"\r\n" + 
				"\"website\": \"rahulshettyacademy.com\"\r\n" + 
				"\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"\"courses\": [\r\n" + 
				"\r\n" + 
				"{\r\n" + 
				"\r\n" + 
				"\"title\": \"Selenium Python\",\r\n" + 
				"\r\n" + 
				"\"price\": 50,\r\n" + 
				"\r\n" + 
				"\"copies\": 6\r\n" + 
				"\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"{\r\n" + 
				"\r\n" + 
				"\"title\": \"Cypress\",\r\n" + 
				"\r\n" + 
				"\"price\": 40,\r\n" + 
				"\r\n" + 
				"\"copies\": 4\r\n" + 
				"\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"{\r\n" + 
				"\r\n" + 
				"\"title\": \"RPA\",\r\n" + 
				"\r\n" + 
				"\"price\": 45,\r\n" + 
				"\r\n" + 
				"\"copies\": 10\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"]\r\n" + 
				"\r\n" + 
				"}";
	}

	public static String addBook(String isbn, String aisle) {
		// TODO Auto-generated method stub
		return "{\r\n" + 
				"    \"name\": \"Learn Appium Automation with Java\",\r\n" + 
				"    \"isbn\": \""+ isbn+"\",\r\n" + 
				"    \"aisle\": \"" + aisle + "\",\r\n" + 
				"    \"author\": \"John foe\"\r\n" + 
				"}";
		
	}
}
