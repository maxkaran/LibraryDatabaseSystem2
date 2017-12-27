package Library;

public class DateReturnedBeforeDateRented extends Exception {
	DateReturnedBeforeDateRented(){
		super("The date returned is before the date the item was rented.");
	}
	
	DateReturnedBeforeDateRented(String message){
		super(message);
	}
}
