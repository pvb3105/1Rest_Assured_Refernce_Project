package restAssuredRefernce;

import io.restassured.RestAssured;
import io.restassured.path.xml.*;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
public class Soap_Reference {

	public static void main(String[] args) {
	//Step 1: Declare baseURI and request body variables
		String BaseURI = "https://www.dataaccess.com";
		String requestBody ="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>100</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>\r\n"
				+ "";
	//Step 2:Fetch request parameters
		XmlPath xml_req=new XmlPath(requestBody);
		String req_param=xml_req.getString("ubinum");
		System.out.println(req_param);
		
	//Step 3:Configure the API and fetch Response body
		RestAssured.baseURI= BaseURI;
		String responseBody=given().header("Content-Type","text/xml; charset=utf-8").body(requestBody).when().post("/webservicesserver/NumberConversion.wso")
		.then().extract().response().getBody().asString();
		System.out.println(responseBody);
		
	//Step 4:Parse the response body and fetch the response parameters
		XmlPath xml_res= new XmlPath(responseBody);
		String result=xml_res.getString("NumberToWordsResult");
		System.out.println(result);
	//Step 5:Validate the response body parameters
		Assert.assertEquals(result,"one hundred ");
	}

}
 	