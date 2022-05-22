import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import files.APIFiles;

public class DynamicPayload {

	@Test
	public void ADDbook() {
		
		RestAssured.baseURI = "http://216.10.245.166";
	String res =	given().log().all().header("Content-Type","application/json")
		.body(APIFiles.Addbook()).when().post("Library/Addbook.php").then()
		.log().all().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js2= new JsonPath(res);
	String id = js2.get("ID");
	System.out.println(id);
		
		
	}

}
