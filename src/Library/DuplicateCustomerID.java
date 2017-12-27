package Library;

public class DuplicateCustomerID extends Exception {
	DuplicateCustomerID(){
		super("This Customer ID already exists.");
	}
	
	DuplicateCustomerID(String message){
		super(message);
	}
}
