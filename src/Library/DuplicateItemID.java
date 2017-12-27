package Library;

public class DuplicateItemID extends Exception {
	DuplicateItemID(){
		super("This Item ID already exists.");
	}
	
	DuplicateItemID(String message){
		super(message);
	}
}
