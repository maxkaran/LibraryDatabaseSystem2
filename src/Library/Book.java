package Library;

public class Book extends Item{
	
	private String authors;
	private String publisher;
	private int year;
	
	public Book(Book book) {//copy constructor
		super(book.getName()); //this will give the new book all the same attributes, except the ID will be unique
		this.authors = book.authors;
		this.publisher = book.publisher;
		this.year = book.year;
	}
	
	public Book(String name, String authors, String publisher, int year) {//manual constructor
		super(name);
		this.authors = authors;
		this.publisher = publisher;
		this.year = year;
	}
	
	public Book(int itemID, String name, String authors, String publisher, int year) throws DuplicateItemID {//manual constructor
		super(name, itemID);																				//includes custom ID
		this.authors = authors;
		this.publisher = publisher;
		this.year = year;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public double getLateFee(int daysLate) {
		double fee = 0.5 * (double)daysLate;
		return fee;
	}

	@Override
	public String toString() {
		String info = "Title: " + this.getName() + "\n";
		info += "Book ID: " + this.getID() + "\n";
		info += "Author(s): " + this.getAuthors() + "\n";
		info += "Publisher: " + this.getPublisher() + "\n";
		info += "Year Published: " + this.getYear() + "\n\n";
		
		return info;
	}

	public boolean equals(Book book) { //checks if all attributes match and returns true if they do
		return((this.getYear() == book.getYear()) && this.getAuthors().equals(this.getAuthors()) && this.getName().equals(book.getName()) && this.getPublisher().equals(book.getPublisher()));
	}

	public Book Clone() {
		return new Book(this);
		
	}
	

}
