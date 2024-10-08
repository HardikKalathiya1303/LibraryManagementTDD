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

    @Test
    void testBorrowBookAlreadyBorrowed() {
        String isbn = getRandomISBN();
        String title = getRandomTitle();
        String author = getRandomAuthor();
        int year = 2021;

        library.addBook(isbn, title, author, year);
        library.borrowBook(isbn);
        String result = library.borrowBook(isbn);
        assertEquals("Book is already borrowed", result);
    }
    @Test
    void testReturnBookSuccess() {
        String isbn = getRandomISBN();
        String title = getRandomTitle();
        String author = getRandomAuthor();
        int year = 2021;

        library.addBook(isbn, title, author, year);
        library.borrowBook(isbn);
        String result = library.returnBook(isbn);
        assertEquals("Book returned successfully", result);
    }
    @Test
    void testReturnBookNotBorrowed() {
        String isbn = getRandomISBN();
        String title = getRandomTitle();
        String author = getRandomAuthor();
        int year = 2021;

        library.addBook(isbn, title, author, year);
        String result = library.returnBook(isbn);
        assertEquals("Book was not borrowed", result);
    }

    @Test
    void testReturnBookNotFound() {
        String result = library.returnBook(getRandomISBN());
        assertEquals("Book not found", result);
    }


    @Test
    void testViewAvailableBooksFail() {
        String isbn1 = getRandomISBN();
        String title1 = getRandomTitle();
        String author1 = getRandomAuthor();
        int year1 = 2021;

        String isbn2 = getRandomISBN();
        String title2 = getRandomTitle();
        String author2 = getRandomAuthor();
        int year2 = 2021;

        // Add books to the library
        library.addBook(isbn1, title1, author1, year1);
        library.addBook(isbn2, title2, author2, year2);

        // Borrow one book
        library.borrowBook(isbn1);

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(2, availableBooks.size()); // Incorrect expectation
        assertEquals(title1, availableBooks.get(0).getTitle()); // Incorrect expectation
    }

    @Test
    void testViewAvailableBooks() {
        String isbn1 = getRandomISBN();
        String title1 = getRandomTitle();
        String author1 = getRandomAuthor();
        int year1 = 2021;

        String isbn2 = getRandomISBN();
        String title2 = getRandomTitle();
        String author2 = getRandomAuthor();
        int year2 = 2021;

        library.addBook(isbn1, title1, author1, year1);
        library.addBook(isbn2, title2, author2, year2);
        library.borrowBook(isbn1);

        List<Book> availableBooks = library.viewAvailableBooks();
        assertEquals(1, availableBooks.size());
        assertEquals(title2, availableBooks.get(0).getTitle());
    }
}