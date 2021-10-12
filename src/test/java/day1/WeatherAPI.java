package day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherAPI {
  @Test(enabled=false, description="Getting weather information of Sepcific City")
  public void getWeather() {
	  RestAssured.given()
	  			 .when()
	  			 .get("https://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=133db62133e42068229d4e153c0b3e0b")
	  			 .then()
	  			 .log()
	  			 .body()
	  			 .statusCode(200);
  }


	@Test(enabled=false,description="Getting weather information of Specific City")
	public void getWeather2() {
		Response res = RestAssured.given()
						.when()
						.get("https://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=133db62133e42068229d4e153c0b3e0b");
		System.out.println(res.prettyPrint());
		System.out.println(res.getTime());
		System.out.println(res.getStatusCode());
		System.out.println(res.getContentType());
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(enabled=true,description="Getting weather information of Specific City")
	public void getWeather3() {
			RestAssured.given()
				
						.queryParam("q","Mumbai")
						.queryParam("appid","133db62133e42068229d4e153c0b3e0b")
						
						.when()
						.get("https://openweathermap.org/data/2.5/weather")
						.then()
						.log()
						.body()
						.statusCode(200);
		
		
		
	}	

	@Test(description="Getting weather information of Specific City")
	public void getWeather4() {
		 Map<String,String> param=new HashMap<String, String>();
		  param.put("q", "Mumbai");	// Some Pre-condition like Authentication
		  param.put("appid", "133db62133e42068229d4e153c0b3e0b");
		  RestAssured.given()
		  .queryParams(param)
		  .when()					// Performs some steps
		  .get("https://api.openweathermap.org/data/2.5/weather")
		  .then()			// Some Post_Condition like Verification
		  .log()			//Print data in Console
		  .body()			//Printing body
		  .statusCode(200);
		  
	}
	
	
	
}