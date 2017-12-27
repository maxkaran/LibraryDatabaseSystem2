package Library;

public class Device extends Item {
	
	private double rentalCost;
	
	public Device(Device device) {//copy constructor
		super(device.getName());
		rentalCost = device.rentalCost;
	}
	
	public Device(String name, double rent) {//manual constructor
		super(name);
		rentalCost = rent;
	}
	
	public Device(int itemID, String name, double rent) throws DuplicateItemID{
		super(name, itemID);
		rentalCost = rent;
	}
	
	public double getRentalCost() {
		return rentalCost;
	}

	public void setRentalCost(double rentalCost) {
		this.rentalCost = rentalCost;
	}

	@Override
	public double getLateFee(int daysLate) {
		double fee = 2*(double)daysLate + rentalCost*0.1;
		return fee;
	}

	@Override
	public String toString() {
		String info = "Rental Cost: $" + rentalCost + "\n";
		info += "Device ID: " + this.getID() +"\n";
		info += "Type of Device: " + this.getName() + "\n\n";
		
		return info;
	}


	public boolean equals(Device device) {
		return (rentalCost == device.rentalCost && this.getName().equals(device.getName()));
	}

	public Device Clone() {
		return new Device(this);
	}

}
