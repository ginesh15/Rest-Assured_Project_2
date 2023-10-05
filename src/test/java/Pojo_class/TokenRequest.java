/***Package declaration for organizing code into a package named "Pojo_class"***/
package Pojo_class;
/***Definition of the TokenRequest class***/
public class TokenRequest {
	
	/***Private instance variables to store username and password**/
    private String username;
    private String password;
   
    /***Constructor for creating a TokenRequest object with initial values for username and password***/
    public TokenRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
     
    /***Getter method to retrieve the username***/
    public String getUsername() {
        return username;
    }
    /***Setter method to set the username***/
    public void setUsername(String username) {
        this.username = username;
    }
    /***Getter method to retrieve the password***/
    public String getPassword() {
        return password;
    }
    /***Setter method to set the password***/
    public void setPassword(String password) {
        this.password = password;
    }
}
