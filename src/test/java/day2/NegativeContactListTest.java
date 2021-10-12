package day2;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;


import static io.restassured.RestAssured.*;

public class NegativeContactListTest {
	
	@Test(enabled=false)
	public void recordNotFound() {
		given()
		.when()
			.get("http://3.13.86.142:3000/contacts/")
		.then()
			.log()
			.body()
			.statusCode(404);
	}
	
	@Test(enabled=true,description="Adding contact with Missing details ")
	  public void addingContactMissingParameter() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  emp.put("jobTitle", "AT");
	  emp.put("company", "LTI");
	  details.put("firstName", "");
	  details.put("lastName","Sharma");
	  details.put("email", "pooja290@gmail.com");
	  details.put("location", loc);
	  details.put("employer", emp);
	  
	  String error= given()
			  	 .header("Content-Type","application/json")
				 .body(details.toJSONString())
				 .when()
				 	.post("http://3.13.86.142:3000/contacts")
				 .then()
					 .log()
					 .body()
					 .statusCode(400)
					 .extract()
					 .path("err");
	  Assert.assertTrue(error.contains("firstName: First Name is required"));

	  }
	
	@Test(enabled=true,description="Adding too many characters ")
	  public void addingContactTooManyCharacters() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  
	  loc.put("city", "MumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbai");
	  loc.put("country", "India");
	  emp.put("JobTitle", "GET");
	  emp.put("company", "LTI");
	  details.put("firstName", "Pooja");
	  details.put("lastName","Sharma");
	  details.put("email", "pooja290@gmail.com");
	  details.put("location", loc);
	  details.put("employer", emp);
	  
	  String error= given()
			  .header("Content-Type","application/json")
				 .body(details.toJSONString())
				 .when()
				 .post("http://3.13.86.142:3000/contacts")
				 .then()
				 .log()
				 .body()
				 .statusCode(400)
				 .extract()
				 .path("err");
	  Assert.assertTrue(error.contains("is longer than the maximum allowed length (30)"));
	  }
	
	@Test(enabled=true,description="Invalid Names")
	  public void addingContactInvalidCharacters() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  emp.put("JobTitle", "GET");
	  emp.put("company", "LTI");
	  details.put("firstName", "1234");
	  details.put("lastName","Sharma");
	  details.put("email", "pooja290@gmail.com");
	  details.put("location", loc);
	  details.put("employer", emp);
	  
	  String error= given()
			  .header("Content-Type","application/json")
				 .body(details.toJSONString())
				 .when()
				 .post("http://3.13.86.142:3000/contacts")
				 .then()
				 .log()
				 .body()
				 .statusCode(400)
				 .extract()
				 .path("err");
	  Assert.assertTrue(error.contains(" Validator failed for path `firstName` with value"));
	  }
	
	@Test(enabled=true,description="Adding too many characters ")
	  public void addingContactNotProperFormat() {
	  JSONObject details=new JSONObject();
	  JSONObject loc=new JSONObject();
	  JSONObject emp=new JSONObject();
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  emp.put("JobTitle", "GET");
	  emp.put("company", "LTI");
	  details.put("firstName", "Pooja");
	  details.put("lastName","Sharma");
	  details.put("email", "pooja290gmail.com");
	  details.put("location", loc);
	  details.put("employer", emp);
	  
	  String error= given()
			  .header("Content-Type","application/json")
				 .body(details.toJSONString())
				 .when()
				 .post("http://3.13.86.142:3000/contacts")
				 .then()
				 .log()
				 .body()
				 .statusCode(400)
				 .extract()
				 .path("err");
	  Assert.assertTrue(error.contains("Validator failed for path `email` with value"));
	  }
}
