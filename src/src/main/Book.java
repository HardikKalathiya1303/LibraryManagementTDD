package src.main;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Assume a new book is available by default
    }

}
