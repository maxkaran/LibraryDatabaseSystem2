package Library;

public class Customer {
	
	public enum Type {Student, Employee}
	
	private int ID;
	private String name;
	private String Department;
	public Type type; //make sure students pay 75 percent what employees do
	
	public Customer(int iD, String name, String department, Type type) {
		super();
		ID = iD;
		this.name = name;
		Department = department;
		this.type = type;
	}
	
	//___________________________________GETTERS + SETTERS__________________________________
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}
	
	
}
