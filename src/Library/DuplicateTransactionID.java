package Library;

public class DuplicateTransactionID extends Exception {
	DuplicateTransactionID(){
		super("This Item ID already exists.");
	}
	
	DuplicateTransactionID(String message){
		super(message);
	}

}
