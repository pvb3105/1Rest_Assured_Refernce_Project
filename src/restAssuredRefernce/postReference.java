package restAssuredRefernce;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

public class postReference {

	public static void main(String[] args) {
		
	//Step 1 : Declare Base Url
		
	RestAssured.baseURI="https://reqres.in/";
	
	//Step 2 : Configure Request Body
	
    //Using log all
	/*String responseBody = given().header("Content-Type","application/json").body("{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"leader\"\r\n"
			+ "}").log().all().when().post("/api/users").then().log().all().extract().response().asString();*/
	
	//Without log all
	
	int statusCode=given().header("Content-Type","application/json").body("{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"leader\"\r\n"
			+ "}").when().post("/api/users").then().extract().statusCode();
	
	String responseBody = given().header("Content-Type","application/json").body("{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"leader\"\r\n"
			+ "}").when().post("/api/users").then().extract().response().asString();
	
    
	System.out.println(statusCode);

	System.out.println(responseBody);
	

	
	//Step 3 : Parse the response Body
	
	JsonPath jsp = new JsonPath(responseBody);
	String res_name=jsp.getString("name");
	String res_job=jsp.getString("job");
	String res_id=jsp.getString("id");
	String res_createdAt=jsp.getString("createdAt");
	
	//Step 4 : Validate the response body parameters
	Assert.assertEquals(statusCode, 201);
	Assert.assertEquals(res_name, "morpheus");
	Assert.assertEquals(res_job, "leader");
	Assert.assertNotNull(res_id);
	
	 //Step 5: Validate "createdAt" using slice method for date
    String expectedDate = new java.util.Date().toInstant().toString().substring(0, 10);
    String actualDate = res_createdAt.substring(0, 10);
    Assert.assertEquals(actualDate, expectedDate);
	Assert.assertEquals(res_createdAt.substring(0,10),expectedDate);
	System.out.println(expectedDate);

	}

}
