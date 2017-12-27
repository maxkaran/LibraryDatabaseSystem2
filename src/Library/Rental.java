package Library;

import java.util.Date;

public class Rental {
	private static int nextTransactionID = 0;
	private static double LATEFEE = 5; //late fee in dollars per day, can be adjusted to new amount if so desired

	private final long DAY = 24*60*60*1000; //milliseconds in a day, used to calculate return date
	
	private Date rentalTime;
	private Date expectedReturnTime;
	private Date actualReturnTime;
	
	private Item rentedItem;
	private int rentalDays;
	private int lateDays;
	private int transactionID;
	private Customer customer;
	
	enum Status{ ACTIVE, LATE, CLOSED}
	
	private Status status;
	
	public Rental(Item rentedItem, Customer customer , int rentalDays) {//constructor
		this.rentedItem = rentedItem;
		this.customer = customer;
		this.rentalDays = rentalDays;
		this.transactionID = ++nextTransactionID;
		this.status = Status.ACTIVE;
		
		rentalTime = new Date();
		expectedReturnTime = new Date();
		expectedReturnTime.setTime(rentalTime.getTime() + (long)rentalDays * DAY); //sets return date by adding the number of milliseconds that
																				   //corresponds with the number of rental days	
		actualReturnTime = null;
	}
	
	public String toString(){//convert this rental to a string
		String s = "Transaction ID: "+transactionID +"   Item ID: "+rentedItem.getID() + "   Item Type: "+rentedItem.getClass().getSimpleName();
		s += "Item Name: "+rentedItem.getName() +"   Customer Name: "+customer.getName()+"   Customer ID: "+customer.getID();
		
		s += "\n";
		return s;
	}
	
	//_________________________________________Status Modifying Functions__________________________________
	
	public boolean isLate(){
		if((actualReturnTime == null && expectedReturnTime.getTime() < (new Date().getTime()))){
			this.status = Status.LATE;
			return true;
		}
		else
			return false;
	}
	
	public void itemReturned(){
		actualReturnTime = new Date();
		this.status = Status.CLOSED;
	}
	
	//_________________________________________Payment Related Functions____________________________________
	
	public double getLateFee(){
		double lateFee = 0;
		Date currentTime = new Date();
		
		long timePassed = currentTime.getTime() - expectedReturnTime.getTime();
		
		if(timePassed < 0)
			return 0; //due date has not passed in this scenario and the patron has no late fee on the rental
		
		int daysPassed = (int)(timePassed/DAY); //converts milliseconds of time passed to the number of days that have passed
		lateFee = (double)daysPassed * LATEFEE;
		
		return lateFee;
	}
	
	public double getRentalCosts(){
		if(rentedItem instanceof Device){
			return (double)rentalDays * ((Device) rentedItem).getRentalCost();
		}
		
		return 0; //if it is a book, there is no rental cost
		
	}
	
	public double getTotalToBePaid(){
		double rent = getRentalCosts();
		double late = getLateFee();
		
		if(customer.type == customer.type.Student)
			rent = 0.75*rent; //applying student discount
		
		return rent + late; //returns total fee
	}
	
	//__________________________________________getters+setters_____________________________________________
	public Date getRentalTime() {
		return rentalTime;
	}
	public Date getExpectedReturnTime() {
		return expectedReturnTime;
	}
	public Status getStatus() {
		return status;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public Item getRentedItem() {
		return rentedItem;
	}
	public void setRentedItem(Item rentedItem) {
		this.rentedItem = rentedItem;
	}
	public int getRentalDays() {
		return rentalDays;
	}
	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}
	public int getLateDays() {
		return lateDays;
	}
	public void setLateDays(int lateDays) {
		this.lateDays = lateDays;
	}
	public static double getLATEFEE() {
		return LATEFEE;
	}
	public static void setLATEFEE(double lATEFEE) {
		LATEFEE = lATEFEE;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setRentalTime(Date rentalTime) {
		this.rentalTime = rentalTime;
	}
	public Date getActualReturnTime() {
		return actualReturnTime;
	}
	public void setActualReturnTime(Date actualReturnTime) {
		this.actualReturnTime = actualReturnTime;
	}
	public void setExpectedReturnTime() { //this is for when the rental dates are changed in main and the expected return date needs to be updated
		expectedReturnTime.setTime(rentalTime.getTime() + (long)rentalDays * DAY);
	}
	
	
	
}
