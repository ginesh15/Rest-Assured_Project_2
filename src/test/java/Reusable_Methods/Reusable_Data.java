package Reusable_Methods;
/***Import necessary libraries***/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import Pojo_class.Booking_data;
import Pojo_class.Bookingdates;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/***Define a class named Reusable_Data***/
public class Reusable_Data {
	
	/***Properties object to hold configuration properties***/
	public static Properties prop;
	
	/***ContentType object for JSON content handling***/
	public static ContentType JSON;
	
	/***Method to create a Booking_data object (POJO) with properties read from properties file***/
	public static Booking_data bookingPayload(){
		
		/***Retrieve data from properties file***/
		String firstname = prop.getProperty("first_name");
		String lastname = prop.getProperty("last_name");
		int totalprice =  Integer.valueOf(prop.getProperty("total_price"));
		boolean depositpaid = Boolean.valueOf(prop.getProperty("deposit_paid"));
		String checkin = prop.getProperty("checkin"); 
	    String checkout = prop.getProperty("checkout");
		String additionalneeds = prop.getProperty("additional_needs");
		
		/***Create a Booking_data object and set its properties***/	
		Booking_data bookingRequestBody = new Booking_data();
	    
		bookingRequestBody.setFirstname(firstname);
		bookingRequestBody.setLastname(lastname);
		bookingRequestBody.setTotalprice(totalprice);
		bookingRequestBody.setDepositpaid(depositpaid);
		
		/***Create a Bookingdates object and set its properties***/
		Bookingdates bookDates = new Bookingdates();
		bookDates.setCheckin(checkin);
		bookDates.setCheckout(checkout);
		
		/***Set the Bookingdates object in the Booking_data object***/
		bookingRequestBody.setBookingdates(bookDates);
		bookingRequestBody.setAdditionalneeds(additionalneeds);
		
		/***Return the constructed object***/
		return bookingRequestBody;
	}
	
	/***Method to create a RequestSpecification object with base URL and headers***/
	 public static RequestSpecification getRequestSpecification() {
	        return new RequestSpecBuilder()
	           .setBaseUri(prop.getProperty("base_Url"))
	          .addHeader("Authorization", "Bearer your-access-token")
	        		 .addHeader("Content-Type", "application/json")
	        		 .addHeader("Accept", "application/json")
	        	     .build(); 
	    }
	 
	 /***Method to create a ResponseSpecification object for expected positive response***/
	public static ResponseSpecification positiveResponse() {
        return new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
           .build();
    }
	
	/***Constructor to initialize properties from a properties file***/ 
	 public Reusable_Data (){
			
			try {
				prop = new Properties();
				FileInputStream ip = new FileInputStream("./src/test/resources/configure.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

    	}
}
	