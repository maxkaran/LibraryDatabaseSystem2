package Library;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import Library.Customer.Type;

public class LibrarySys {
	
	final static double laptopPrice = 35; //price to rent laptops per day in dollars
	final static double adapterPrice = 8; //price to rent adapters per day in dollars
	
	//_________________Various HashMaps + ArrayList_____________________________________________________
	
	HashMap<Integer, Customer> customerMap = new HashMap<Integer, Customer>();
	HashMap<Integer, Rental> transactionMap = new HashMap<Integer, Rental>();
	HashMap<Integer, Item> itemMap = new HashMap<Integer, Item>();
	
	ArrayList<Rental> rentals = new ArrayList<Rental>();//ArrayList is necessary for various search functions
	
	//____________________Adding Items, Customers and Transactions Methods Below_________________________
	
	public void addItem(Item item) throws DuplicateItemID, WrongRentalCost{
		try{	
				if(itemMap.containsKey(item.getID())) //check if this ID is already in use
						throw new DuplicateItemID();
				itemMap.put(item.getID(), item);
				
				if((item instanceof Laptop && ((Laptop) item).getRentalCost() != laptopPrice) || (item instanceof Adapter && ((Adapter) item).getRentalCost() != adapterPrice)){
					throw new WrongRentalCost();
				}
						
				
		}		
		catch(DuplicateItemID e){//will generate a new ID for the item, if chosen ID already exists
			while(itemMap.containsKey(item.getID())){
				item.setID(item.getNextID());
			}
			
			//once unique ID has been generated, add to itemMap
			itemMap.put(item.getID(), item);
			System.out.println("Duplicate Item ID: Unique ID has been generated.");
		}
		catch(WrongRentalCost e){ //this will correct the rental cost to the standard price
			if(item instanceof Laptop)
				((Laptop) item).setRentalCost(laptopPrice);
			else if(item instanceof Adapter)
				((Adapter) item).setRentalCost(adapterPrice);
			
			System.out.println("This is not the correct rental cost, the cost has been set to the standard price.");
		}
	}
	
	public void addCustomer(int iD, String name, String department, Type type) throws DuplicateCustomerID{
		Customer newCustomer = new Customer(iD, name, department, type);
		try{
			if(customerMap.containsKey(iD)) //check if this ID is already in use
				throw new DuplicateCustomerID();
			
			customerMap.put(newCustomer.getID(), newCustomer);
		}
		catch(DuplicateCustomerID e){
			while(customerMap.containsKey(iD)){
				iD++;
			}
			
			newCustomer.setID(iD); //sets unique ID for the customer
			
			//once unique ID has been generated, add to itemMap
			customerMap.put(iD, newCustomer);
			System.out.println("Duplicate Customer ID: Unique ID has been generated.");
		}
	}
	
	public void addTransaction(Item rentedItem, Customer customer , int rentalDays) throws DuplicateTransactionID{
		Rental newRental = new Rental(rentedItem, customer , rentalDays);
		try{
			if(transactionMap.containsKey(newRental.getTransactionID()))
				throw new DuplicateTransactionID();
			
			transactionMap.put(newRental.getTransactionID(), newRental);
			rentals.add(newRental); //adds to arraylist for search purposes
		}
		catch(DuplicateTransactionID e){
			System.out.println("Transaction Creation Failed: Duplicated Transaction ID.");
		}
		

	}
	
	//____________________Method for a returned item that returns the cost for late and rental fees______________

	public double closeTransaction(int transactionID) throws DateReturnedBeforeDateRented, TransactionAlreadyClosed{
		double cost;
		
		Rental rental = transactionMap.get(transactionID); //finding the rented item in the hashmap
		
		try{
			if(rental.getStatus() == Rental.Status.CLOSED) //throws exception if transaction is already closed
				throw new TransactionAlreadyClosed();
			
			if(rental.getRentalTime().after(new Date())) //throws exception if return date is before rental date
				throw new DateReturnedBeforeDateRented();
			
			rental.itemReturned();
			
			cost = rental.getLateFee();
			cost += rental.getRentalCosts();
		}
		catch(DateReturnedBeforeDateRented e){
			System.out.println("Date returned is before the date item was rented.");
			cost = 0;
		}		
		catch(TransactionAlreadyClosed e){
			System.out.println("This transaction is already closed.");
			cost = 0;
		}
		
		return cost;
	}
	
	public ArrayList<Rental> searchByDate(String startDate, String endDate) throws ParseException{ //formatting for dates is mm/dd/yyyy
		ArrayList<Rental> search = new ArrayList<Rental>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		
		Date dateS = sdf.parse(startDate);
		Date dateE = sdf.parse(endDate);
		
		for(Rental rent : rentals){
			if(rent.getRentalTime().after(dateS) && rent.getRentalTime().before(dateE))
				search.add(rent);
		}
		
		return search;
	}
}
