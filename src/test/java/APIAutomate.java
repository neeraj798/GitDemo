import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import org.testng.Assert;

import files.APIFiles;

import static org.hamcrest.Matchers.*;
public class APIAutomate {

	public static void main(String[] args) {
		
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		String response1 = given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(APIFiles.Addplace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response1);
		
		JsonPath js = new JsonPath(response1);
		String placeId = js.getString("place_id");
		System.out.println(js.getString("place_id"));
		
		//Updating the address in the API body
		
		given().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\"70 Summer walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

		//getting the place of the updated address
		
		String Response2 = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).when()
		.get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200)
		.extract().response().asString();
		
		JsonPath js1 = new JsonPath(Response2);
		String actual_address = js1.getString("address");
		System.out.println(js1.getString("address"));
		
	}


}
