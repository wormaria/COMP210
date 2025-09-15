package assn02;

public class Book {
    private String title;
    private String author;
    private boolean checkedOut;


    // Constructor
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.checkedOut = false; // default
    }


    // Mark the book as checked out
    public void checkOut() {
        this.checkedOut = true;
    }


    // Mark the book as returned
    public void returnBook() {
        this.checkedOut = false;
    }


    // Check if the book is checked out
    public boolean isCheckedOut() {
        return this.checkedOut;
    }


    // Display one line of book info with index
    public String displayLine(int index) {
        return index + ". Title: " + title + ", Author: " + author
                + ", Checked Out: " + (checkedOut ? "Yes" : "No");
    }
}
