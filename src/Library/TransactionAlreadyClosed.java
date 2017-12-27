package Library;

public class TransactionAlreadyClosed extends Exception { //additional exception in case user tries to close a closed transaction
	TransactionAlreadyClosed(){
		super("This transaction is already closed.");
	}
	
	TransactionAlreadyClosed(String message){
		super(message);
	}
}
