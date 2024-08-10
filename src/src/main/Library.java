package src.main;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

public class Library {
    private final Map<String, Book> books;

    public Library() {
        books = new HashMap<>();
    }

    public void addBook(String isbn, String title, String author, int publicationYear) {
        books.put(isbn, new Book(isbn, title, author, publicationYear));
        System.out.println("Book added: " + title);
    }

    public String borrowBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return "Book not found";
        }
        if (!book.isAvailable()) {
            return "Book is already borrowed";
        }
        book.setAvailable(false);
        return "Book borrowed successfully";
    }

    public String returnBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return "Book not found";
        }
        if (book.isAvailable()) {
            return "Book was not borrowed";
        }
        book.setAvailable(true);
        return "Book returned successfully";
    }

    public List<Book> viewAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Entry<String, Book> entry : books.entrySet()) {
            if (entry.getValue().isAvailable()) {
                availableBooks.add(entry.getValue());
            }
        }
        return availableBooks;
    }
}