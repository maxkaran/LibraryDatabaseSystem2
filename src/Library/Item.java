package Library;

public abstract class Item {
	private static int nextID = 0;//static int that is incremented by one and set as item ID whenever new item entry is created
	private int ID;
	private String name;
	
	public Item(String name) {
		ID = ++nextID;
		this.name = name;
	}
	
	public Item(String name, int ID){	
		this.name = name;
		this.ID = ID;
	}
	
	
	//setters & getters, note there is no ID setter since it is a static variable that increments when a new item is created
	public int getID() {
		return ID;
	}
	
	public void setID(int id){
		this.ID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getNextID(){
		return ++nextID; //increments to next ID and returns it for user
	}



	//set the methods that all subclasses will need to implement individually
	public abstract double getLateFee(int daysLate);
	public abstract String toString();
}
