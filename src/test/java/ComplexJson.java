import files.APIFiles;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		int Sum =0;
		JsonPath js = new JsonPath(APIFiles.Courseprice());
		int count = js.getInt("courses.size()");
		System.out.println("The number of courses are :" +count);
		int Purchase_Amount = js.getInt("dashboard.purchaseAmount");
		System.out.println("The Purchase Amount of courses are :" +Purchase_Amount);
		//System.out.println(js.get("courses[0].title"));
		for(int i=0;i<count;i++)
		{
			//String coursetitle = js.getString("courses["+i+"].title");
			int  price = js.get("courses["+i+"].price");
			int copies = js.get("courses["+i+"].copies");
			int Amount = price*copies;
			Sum = Sum+Amount;
			System.out.println("The purchase amount is :"+Amount);
		}
		System.out.println("The purchase amount is :"+ Sum);
		
	}

}
