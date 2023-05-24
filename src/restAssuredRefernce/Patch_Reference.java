package restAssuredRefernce;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Patch_Reference {

	public static void main(String[] args) {
		RestAssured.baseURI="https://reqres.in/";
		
		int statusCode=given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").when().patch("/api/users/2").then().extract().statusCode();
		
		String responseBody = given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}").when().patch("/api/users/2").then().extract().response().asString();
		
	    
		System.out.println(statusCode);

		System.out.println(responseBody);
		JsonPath jsp = new JsonPath(responseBody);
		String res_name=jsp.getString("name");
		String res_job=jsp.getString("job");
		
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(res_name, "morpheus");
		Assert.assertEquals(res_job, "zion resident");
	}

}
