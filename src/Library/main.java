package Library;

import java.util.Date;

//_________________________________FOR TESTING____________________________________________

public class main {

	public static void main(String[] args) throws DuplicateItemID, WrongRentalCost, DuplicateCustomerID, DuplicateTransactionID, DateReturnedBeforeDateRented, TransactionAlreadyClosed {
		
		LibrarySys lib = new LibrarySys(); //create a new library database
		
		//__________________________Adding items to the collection__________________________________
		lib.addItem(new Book("Atlas Shrugged", "Ayn Rand", "New York Print", 1978));
		lib.addItem(new Device("Kobo EReader", 20));
		lib.addItem(new Adapter(45, LibrarySys.adapterPrice)); //custom ID constructor
		lib.addItem(new Adapter(2)); //demonstrate the wrong rental cost transaction
		lib.addItem(new Textbook(45, "Grade 12 Biology", "Sean Smith, Dan Harbours, Evan King", "Scholastic", 2013)); //demonstrate duplicate ID exception ));
		lib.addItem(new Laptop("Lenovo Y50", LibrarySys.laptopPrice));
		
		//__________________Creating Customers___________________________________________________
		lib.addCustomer(6904, "Ted Smith", "Geology", Customer.Type.Employee);
		lib.addCustomer(6750, "Alana Bethel", "Biology", Customer.Type.Student);
		lib.addCustomer(5555, "Neil Degrasse Tyson", "Physics", Customer.Type.Employee);
		lib.addCustomer(5555, "Susan Eren", "Computer Science", Customer.Type.Employee); //demonstrate duplicate ID exception
		
		
		//______________________________Create some transactions______________________________________		
		lib.addTransaction(lib.itemMap.get(5), lib.customerMap.get(5555), 4); //Neil takes the laptop for 4 days
		lib.addTransaction(lib.itemMap.get(1), lib.customerMap.get(5556), 7); //Susan takes Atlas Shrugged for 7 days
		lib.addTransaction(lib.itemMap.get(45), lib.customerMap.get(6904), 1); //Ted takes adaptor for 1 day
		lib.addTransaction(lib.itemMap.get(2), lib.customerMap.get(6904), 5); //Ted takes EReader for 5 days
		lib.addTransaction(lib.itemMap.get(4), lib.customerMap.get(6750), 14); //Alana takes Biology textbook for 2 weeks

		//____________Change dates on when the were taken to demonstrate late + rental fees___________
		lib.transactionMap.get(1).setRentalTime(new Date(117, 2, 24)); //first transaction happened march 24
		lib.transactionMap.get(2).setRentalTime(new Date(117, 2, 20)); //second transaction happened march 20
		lib.transactionMap.get(3).setRentalTime(new Date(117, 2, 23)); //third transaction happened march 23
		lib.transactionMap.get(4).setRentalTime(new Date(117, 2, 22)); //fourth transaction happened march 22
		lib.transactionMap.get(5).setRentalTime(new Date(117, 2, 7)); //fifth transaction happened march 7

		//Unfortunately, I need to reset my expected return times since I have changed my rental times as well
		lib.transactionMap.get(1).setExpectedReturnTime();
		lib.transactionMap.get(2).setExpectedReturnTime();
		lib.transactionMap.get(3).setExpectedReturnTime();
		lib.transactionMap.get(4).setExpectedReturnTime();
		lib.transactionMap.get(5).setExpectedReturnTime();
		
		//______________Close all transactions and calculate late fees and total fees________________________
		double lateFees = 0, totFees = 0;
		
		System.out.println();
		for(int i=1;i<6;i++){
			lateFees += lib.transactionMap.get(i).getLateFee();
			if(lib.transactionMap.get(i).isLate()){
				System.out.println(lib.transactionMap.get(i).toString());
			}
				
		}
		
		for(int i=1;i<6;i++){
			totFees += lib.closeTransaction(i);
		}
		
		lib.closeTransaction(3); //demonstrate transaction already closed exception
		
		System.out.println("Total Late Fees: $" +lateFees+ "\nTotal Fees: $" +totFees);
	}

}
