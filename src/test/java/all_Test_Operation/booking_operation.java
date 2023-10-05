/***Importing necessary classes and packages***/
package all_Test_Operation;
import org.testng.annotations.Test;
import Pojo_class.Booking_data;
import Pojo_class.TokenRequest;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import Reusable_Methods.Reusable_Data;
import Utilities.LogUtil;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/***Adding a listener class to this test class***/
@Listeners(Utilities.ListnerImplementaion.class)

public class booking_operation extends Reusable_Data {
	
	/***Variables to store booking ID and token***/		
	static String bookingId = "";
	String dynamicToken ="";
	
	/***Creating a Booking_data object with booking payload***/
	Booking_data bookingRequest = bookingPayload();
	
	/***Initializing logger and request/response specifications***/
	private static Logger LogObj = LogManager.getLogger(LogUtil.class);
	RequestSpecification requestSpec = Reusable_Data.getRequestSpecification();
	ResponseSpecification RESPONSE_SPEC = Reusable_Data.positiveResponse();
	
	/***This method is executed before any test in the class***/
    @BeforeClass
	 public void token_genaration(){
    	
    	    /***Fetching user credentials from property file***/
	    	String user = prop.getProperty("user_name");
	    	String pass_word = prop.getProperty("password");
	    	
	    	/***Creating a TokenRequest object with user credentials***/     
	        TokenRequest tokenRequest = new TokenRequest(user, pass_word);
	        
	        /***Sending a POST request to generate a token***/
	        Response response = RestAssured.given()
	           .spec(requestSpec)
	            .body(tokenRequest)
	            .when() 
	            .post("/auth");
	        
	        /***Extracting the generated token from the response***/
	        dynamicToken = response.jsonPath().getString("token");
	        System.out.println("Generated Token: " + dynamicToken);
	        LogObj.info("******Token Generated Successfully******\n");
	     			
	    }

    /***Test to get booking IDs and their details***/ 
	@Test(priority=1,description="Returns the ids of all the bookings")	
	public void get_bookingID() {	
		
		/***Sending a GET request to get booking IDs***/
		Response response = RestAssured.given()
				.spec(requestSpec)
				.when()
				.get("/booking").then().spec(RESPONSE_SPEC)
				.extract().response();
		
		    /***Extracting and processing booking IDs***/
 			List<Object> bookingIdsObjects = JsonPath.from(response.getBody().asString()).getList("bookingid");

			List<Integer> bookingIds = bookingIdsObjects.stream()
					.map(obj -> Integer.parseInt(obj.toString()))
					.limit(3)// Limiting to 3 booking IDs
					.collect(Collectors.toList());
            
			System.out.println("Total Booking IDs: " + bookingIds.size());
			System.out.println("Limited Booking IDs numbers: " + bookingIds);
			
			/***Fetching and printing details for each booking ID***/
			for (Integer bookingId : bookingIds) {
				Response bookingResponse = RestAssured.given()
						.spec(requestSpec)
						.when()
						.get("/booking/" + bookingId).then().spec(RESPONSE_SPEC)
						.extract().response();

				
					System.out.println("Booking ID: " + bookingId);
					System.out.println("Booking Details: " + bookingResponse.getBody().asString());
			}
		LogObj.info("******Get All booking id with details Created Successfully******\n");
	}
	
	
	/***Test to create a new booking***/
	@Test(priority=2,description="Creates a new booking in the API")
	public void post_createBooking() {
		
		/***Sending a POST request to create a booking***/		
		Response createResponse = RestAssured.given()
			    .spec(requestSpec)
				.body(bookingRequest)
				.when()
				.post("/booking").then()
				.spec(RESPONSE_SPEC)
				.extract().response();
		
		 /***Extracting booking ID from the response***/
		 bookingId = createResponse.jsonPath().getString("bookingid");
		System.out.println("Booking Details of create booking: " + createResponse.getBody().asString());
			
			String name = createResponse.jsonPath().getString("booking['firstname']");
			Assert.assertEquals(name, "John");
		LogObj.info("******Create the Booking successfully******\n");
	}
	
	/***Test to get details of a booking***/
	@Test(priority=3,description="Get a new booking in the API")
	public void get_booking() {
		
		/***Sending a GET request to get booking details using the created booking ID***/
		Response getResponse = RestAssured.given()
				.spec(requestSpec)
				.when()
				.get("/booking/" + bookingId).then().spec(RESPONSE_SPEC)
				.extract().response();

		    System.out.println("Booking ID of Created Booking: " + bookingId);
			System.out.println("Booking Details: " + getResponse.getBody().asString());
			String name = getResponse.jsonPath().getString("firstname");
			Assert.assertEquals(name, "John");
		LogObj.info("******Get the current booking details successfully******\n");
	}
	
	/***Test to update a booking***/
	@Test(priority=4,description="Updates a current booking in the API")
	public void put_updateBooking() {
		
		String updatedData = prop.getProperty("updated_data");
		
		/***Sending a PUT request to update booking details***/	
		Response UpdateResponse = RestAssured.given()
				.spec(requestSpec)
				.cookie("token", dynamicToken) 
				.body(updatedData)
				.when()
				.put("/booking/" + bookingId).then().spec(RESPONSE_SPEC)
				.extract().response();

		    System.out.println("Booking ID " + bookingId + " updated successfully.");
			System.out.println("Booking Details: " + UpdateResponse.getBody().asString());
			String name = UpdateResponse.jsonPath().getString("firstname");
			Assert.assertEquals(name, "UpdatedFirstName");
		LogObj.info("******Update the current booking successfully******\n");

	}
	
	/***Test to partially update a booking***/
	@Test(priority=5,description="Partial Update the current booking in the API")
	public void patch_partial_updateBooking() {
		
		String updatedData = prop.getProperty("partial_updated_data");
		
		/***Sending a PATCH request to partially update booking details***/		
		Response Partial_updateResponse = RestAssured.given()
				.spec(requestSpec)
				.cookie("token", dynamicToken) 
				.body(updatedData)
				.when()
				.patch("/booking/" + bookingId).then().spec(RESPONSE_SPEC)
				.extract().response();

		    System.out.println("Booking ID " + bookingId + " Partial_updated successfully.");
			System.out.println("Booking Details: " + Partial_updateResponse.getBody().asString());
			String name = Partial_updateResponse.jsonPath().getString("firstname");
			Assert.assertEquals(name, "john");
		LogObj.info("******Partial update like first name and last name update successfully******\n");

	}
	
	/***Test to delete a booking***/
	@Test(priority=6,description="Delete the current booking in the ApI")
	public void delete_booking() {
		
		/***Sending a DELETE request to delete a booking***/
		 Response deleteResponse = RestAssured.given()
				 .spec(requestSpec)
				 .cookie("token", dynamicToken) 
		            .when()
		            .delete("/booking/" + bookingId).then().statusCode(201)
					.extract().response(); 

		     System.out.println("Booking ID " + bookingId + " has been deleted.");
		        LogObj.info("******Delete the current booking Successfully******\n\n");
	}


}
