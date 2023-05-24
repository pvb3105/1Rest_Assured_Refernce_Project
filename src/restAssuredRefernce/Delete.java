package restAssuredRefernce;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;

public class Delete {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://reqres.in/";
	    
	    int statusCode=given().when().delete("/api/users/2").then().extract().statusCode();	
	    String responseBody = given().when().delete("/api/users/2").then().extract().response().asString();
			
	    System.out.println(statusCode);

		System.out.println(responseBody);
		Assert.assertEquals(statusCode, 204);
	}

}
