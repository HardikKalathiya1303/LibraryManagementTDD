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

    public List<Book> viewAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Entry<String, Book> entry : books.entrySet()) {
            if (entry.getValue().isAvailable()) {
                availableBooks.add(entry.getValue());
            }
        }
        return availableBooks;
    }
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Available Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbnAdd = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String titleAdd = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String authorAdd = scanner.nextLine();
                    System.out.print("Enter Publication Year: ");
                    int yearAdd = scanner.nextInt();
                    scanner.nextLine();
                    library.addBook(isbnAdd, titleAdd, authorAdd, yearAdd);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }


}