package day2;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GitHub {
	
	@BeforeTest
	public void beforeTest() {
		baseURI = "https://api.github.com/user/repos";
		authentication= oauth2("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");	
	}
	
	
	@Test(enabled = true)
	public void gettingALlRepositories() {
		given()
			.auth().oauth2("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")
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.log()
			.body()
			.statusCode(200);
	}
	
	@Test(enabled=true)
	public void createRepositories() {
		JSONObject data = new JSONObject();
		data.put("name", "RestAssuredCreations1");
		data.put("description", "I am created By RestAsurred Tool");
		data.put("homepage", "https://github.com/poojasharma2970");
		given()
			.auth()
			.oauth2("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")
			.header("Content-Type","application/json")
			.body(data.toJSONString())
		.when()
			.post("https://api.github.com/user/repos")
		.then()
			.log()
			.body()
			.statusCode(201)
			.time(Matchers.lessThan(1000L), TimeUnit.MILLISECONDS);
	}
}
