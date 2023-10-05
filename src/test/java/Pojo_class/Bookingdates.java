/***Import necessary libraries***/
package Pojo_class;

/***This is a class named "Bookingdates" that represents booking date information.***/
public class Bookingdates {

	/***Declaration of a public instance variable named "checkin" to store the check-in date.***/
	public String checkin;
	
	/***Declaration of a public instance variable named "checkout" to store the check-out date.***/
    public String checkout;
    
    /***This is a getter method for retrieving the check-in date.***/
	public String getCheckin() {
		return checkin;
	}
	
	/***This is a setter method for setting the check-in date.***/
	public void setCheckin(String checkin) {
		
		/***Assigns the provided check-in date to the instance variable "checkin".***/
		this.checkin = checkin;
	}
	
	/***This is a getter method for retrieving the check-out date.***/
	public String getCheckout() {
		return checkout;
	}
	
	/***This is a setter method for setting the check-out date***/
	public void setCheckout(String checkout) {
		
		/***Assigns the provided check-out date to the instance variable "checkout".***/
		this.checkout = checkout;
	}
}
