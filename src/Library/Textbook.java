package Library;

public class Textbook extends Book{

	public Textbook(Textbook textbook) {//copy constructor
		super(textbook.getName(), textbook.getAuthors(), textbook.getPublisher(), textbook.getYear());
	}
	
	public Textbook(String name, String authors, String publisher, int year) {//manual constructor
		super(name, authors, publisher, year);
	}

	public Textbook(int itemID, String name, String authors, String publisher, int year) throws DuplicateItemID {//manual constructor
		super(itemID, name, authors, publisher, year); //allows for custom ID
	}

	@Override
	public double getLateFee(int daysLate) {
		return daysLate;
	}
	
	@Override
	public String toString() {
		String info = "Title: " + this.getName() + "\n";
		info += "Textook ID: " + this.getID() + "\n"; //change to specify textbook
		info += "Author(s): " + this.getAuthors() + "\n";
		info += "Publisher: " + this.getPublisher() + "\n";
		info += "Year Published: " + this.getYear() + "\n\n";
		
		return info;
	}
	
	public Textbook Clone() {
		return new Textbook(this);
	}


}
