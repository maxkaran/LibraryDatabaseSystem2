package Library;

public class Laptop extends Device {

	public Laptop(Laptop laptop) {//copy constructor
		super(laptop.getName(), laptop.getRentalCost());
	}
	
	public Laptop(String name, double rent) {//manual constructor
		super(name, rent);
	}
	
	public Laptop(int itemID, String name, double rent) throws DuplicateItemID {//manual constructor
		super(itemID, name, rent);
	}

	@Override
	public double getLateFee(int daysLate) {
		double fee = 5*(double)daysLate + this.getRentalCost()*0.2;
		return fee;
	}
	
	@Override
	public String toString() {
		String info = "Rental Cost: $" + this.getRentalCost() + "\n";
		info += "Laptop ID: " + this.getID() +"\n";
		info += "Type of Device: Laptop\nModel: "+getName()+"\n\n"; //type is known
		
		return info;
	}
}
