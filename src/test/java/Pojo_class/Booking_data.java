/***Importing the Reusable_Data class from the Reusable_Methods package. with neccessary package***/
package Pojo_class;
import Reusable_Methods.Reusable_Data;

/***Defining the Booking_data class which extends Reusable_Data.***/
public class Booking_data extends Reusable_Data{
	
	/***Declaring instance variables to store booking data.***/
	public String firstname;
    public String lastname;
    public int totalprice;
    public boolean depositpaid;
    public Bookingdates bookingdates;
    public String additionalneeds;
    
    /***Getter method for retrieving the first name.***/
	public String getFirstname() {
		return firstname;
	}
	/***Setter method for setting the first name.***/
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/***Getter method for retrieving the last name.***/
	public String getLastname() {
		return lastname;
	}
	/***Setter method for setting the last name.***/
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/***Getter method for retrieving the total price.***/
	public int getTotalprice() {
		return totalprice;
	}
	/***Setter method for setting the total price.***/
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	/***Getter method for checking if a deposit is paid.***/
	public boolean isDepositpaid() {
		return depositpaid;
	}
	/***Setter method for setting the deposit paid status.***/
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}
	/***Getter method for retrieving booking dates.***/
	public Bookingdates getBookingdates() {
		return bookingdates;
	}
	/***Setter method for setting booking dates.***/
	public void setBookingdates(Bookingdates bookingdates) {
		this.bookingdates = bookingdates;
	}
	/***Getter method for retrieving additional needs.***/
	public String getAdditionalneeds() {
		return additionalneeds;
	}
	/***Setter method for setting additional needs.***/
	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}
    
	 	    
}

