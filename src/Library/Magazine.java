package Library;

public class Magazine extends Book {

	public Magazine(Magazine magazine) {//copy constructor
		super(magazine.getName(), magazine.getAuthors(), magazine.getPublisher(), magazine.getYear());
	}
	
	public Magazine(String name, String authors, String publisher, int year) {//manual constructor
		super(name, authors, publisher, year);
	}
	
	public Magazine(int itemID, String name, String authors, String publisher, int year) throws DuplicateItemID {//manual constructor
		super(itemID, name, authors, publisher, year); //allows for custom ID
	}

	@Override
	public double getLateFee(int daysLate) {
		return 0.75*(double)daysLate;
	}
	
	@Override
	public String toString() {
		String info = "Title: " + this.getName() + "\n";
		info += "Magazine ID: " + this.getID() + "\n"; //change to specify magazine
		info += "Author(s): " + this.getAuthors() + "\n";
		info += "Publisher: " + this.getPublisher() + "\n";
		info += "Year Published: " + this.getYear() + "\n\n";
		
		return info;
	}

	public Magazine Clone() {
		return new Magazine(this);
	}

}
