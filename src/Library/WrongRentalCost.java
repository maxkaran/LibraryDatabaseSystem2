package Library;

public class WrongRentalCost extends Exception {
	WrongRentalCost(){
		super("This is the wrong rental cost.");
	}
	
	WrongRentalCost(String message){
		super(message);
	}
}
