package src.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import src.main.Library; // Import the Library class
import src.main.Book; // Import the Book class

class LibraryTest {

    private Library library;
    private static final String[] AUTHORS = {
            "Bhavik Sarkhedi", "Rabindranath Tagore", "Salman Rushdie",
            "Arundhati Roy", "R.K. Narayan", "Jhumpa Lahiri",
            "Mulk Raj Anand", "Ruskin Bond", "Chetan Bhagat", "Vikram Seth"
    };
    private static final String[] TITLES = {
            "The Immortals of Meluha", "The Secret of the Nagas",
            "The Oath of the Vayuputras", "Mahabharata",
            "The Great War of Hind", "Ramayana",
            "The Palace of Illusions", "Jaya"
    };
    private Random random;

    @BeforeEach
    void setUp() {
        library = new Library();
        random = new Random();
    }

    private String getRandomISBN() {
        return String.format("%05d", random.nextInt(100000));
    }

    private String getRandomAuthor() {
        return AUTHORS[random.nextInt(AUTHORS.length)];
    }

    private String getRandomTitle() {
        return TITLES[random.nextInt(TITLES.length)];
    }

    @Test
    void testAddBookWithInvalidISBN() {
        String isbn = "INVALID_ISBN"; // Invalid ISBN
        String title = getRandomTitle();
        String author = getRandomAuthor();
        int year = 2021;

        // This should fail because the system doesn't handle invalid ISBNs yet
        library.addBook(isbn, title, author, year);
        List<Book> availableBooks = library.viewAvailableBooks();
        assertTrue(availableBooks.isEmpty(), "Expected no books to be added with invalid ISBN");
    }
    @Test
    void testAddBook() {
        String isbn = getRandomISBN();
        String title = getRandomTitle();
        String author = getRandomAuthor();
        int year = 2021;

        library.addBook(isbn, title, author, year);
        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(1, availableBooks.size());
        assertEquals(title, availableBooks.get(0).getTitle());
    }
    @Test
    void testBorrowBookInvalidISBN() {
        String result = library.borrowBook("INVALID_ISBN"); // Invalid ISBN
        assertEquals("Unexpected result", result); // Incorrect expected value
    }
    @Test
    void testBorrowBookSuccess() {
        String isbn = getRandomISBN();
        String title = getRandomTitle();
        String author = getRandomAuthor();
        int year = 2021;

        library.addBook(isbn, title, author, year);
        String result = library.borrowBook(isbn);
        assertEquals("Book borrowed successfully", result);
    }

}