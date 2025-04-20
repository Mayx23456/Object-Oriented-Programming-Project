package Part1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a library that manages a collection of books.
 * Provides functionality to add, remove, borrow, return, and list books.
 */
public class Library {

    private static ArrayList<LibraryBook> books;

    /**
     * Constructs a new Library with an empty list of books.
     */
    public Library() {
        books = new ArrayList<>();
    }

    /**
     * Searches for a book by its ID.
     *
     * @param id The ID of the book to search for.
     * @return The book with the specified ID, or null if not found.
     */
    public LibraryBook search(int id) {
        for (LibraryBook book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    /**
     * Borrows a book by its ID if it is available.
     *
     * @param id The ID of the book to borrow.
     * @return true if the book was successfully borrowed, false otherwise.
     */
    public boolean borrowBook(int id) {
        LibraryBook book = search(id);
        if (book != null && book.getStatus() == BookStatus.AVAILABLE) {
            return book.checkout();
        }
        return false;
    }

    /**
     * Returns a borrowed book by its ID.
     *
     * @param id The ID of the book to return.
     * @return true if the book was successfully returned, false otherwise.
     */
    public boolean returnBook(int id) {
        LibraryBook book = search(id);
        if (book != null && book.getStatus() == BookStatus.ON_LOAN) {
            return book.checkin();
        }
        return false;
    }

    /**
     * Adds a new book to the library if it does not already exist.
     *
     * @param bk The book to add.
     * @return true if the book was successfully added, false otherwise.
     */
    public boolean add(LibraryBook bk) {
        for (LibraryBook book : books) {
            if (bk.getId() == book.getId()) {
                return false;
            }
        }
        books.add(bk); 
        return true;
    }

    /**
     * Collects details for a new book from the user and adds it to the library.
     */
    public void collectDetails() {
        Scanner input = new Scanner(System.in);
        boolean loop = true;

        do {
            System.out.println("Title: ");
            String title = input.nextLine();

            System.out.println("Author: ");
            String author = input.nextLine();

            System.out.println("ISBN: ");
            String isbn = input.nextLine();

            System.out.println("Type (Fiction (F), Non-Fiction (N), Reference (R)): ");
            String btype = input.nextLine();
            BookType type = BookType.REFERENCE;
            
            if (btype.equalsIgnoreCase("F") || btype.equalsIgnoreCase("Fiction")) {
                type = BookType.FICTION;
            } else if (btype.equalsIgnoreCase("N") || btype.equalsIgnoreCase("Non-Fiction")) {
                type = BookType.NON_FICTION;
            }

            System.out.println("Edition: ");
            int edition = input.nextInt();
            input.nextLine();

            System.out.println("Summary: ");
            String summary = input.nextLine();

            System.out.println("Price: ");
            double price = input.nextDouble();
            input.nextLine();

            System.out.println("Cover Image: ");
            String coverImage = input.nextLine();

            LibraryBook bk = new LibraryBook(title, author, isbn, type, edition, summary, price, coverImage);
            boolean valid = isValidBook(bk);
            if (valid) {
                add(bk);
                loop = false;
            } else {
                System.out.println("Invalid book. Cannot be added.");
            }

        } while (loop);
        input.close();
    }

    /**
     * Validates if a book meets the required criteria.
     *
     * @param bk The book to validate.
     * @return true if the book is valid, false otherwise.
     */
    public boolean isValidBook(LibraryBook bk) {
        return bk.getTitle() != null && bk.getAuthor() != null && bk.getIsbn().matches("\\d{10}") &&
               bk.getSummary() != null && bk.getPrice() > 0.00;
    }

    /**
     * Removes a book by setting its status to WITHDRAW.
     *
     * @param id The ID of the book to remove.
     * @return true if the book was successfully removed, false otherwise.
     */
    public boolean remove(int id) {
        LibraryBook book = search(id);
        if (book != null) {
            book.setStatus(BookStatus.WITHDRAW);
            return true;
        }
        return false;
    }

    /**
     * Returns an array of books sorted by loan count in descending order.
     *
     * @return An array of books sorted by popularity.
     */
    public LibraryBook[] mostPopular() {
        LibraryBook[] result = list();
        
        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j].getLoanCount() < result[j + 1].getLoanCount()) {
                    // Swap books
                    LibraryBook temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }
        return result;
    }

    /**
     * Returns an array of all books in the library.
     *
     * @return An array of all books.
     */
    public LibraryBook[] list() {
        return books.toArray(new LibraryBook[0]);
    }

    /**
     * Returns an array of books filtered by status.
     *
     * @param stat The status to filter by.
     * @return An array of books with the specified status.
     */
    public LibraryBook[] list(BookStatus stat) {
        LibraryBook[] bookstatus = new LibraryBook[books.size()];
        int i = 0;
        for (LibraryBook book : books) {
            if (book.getStatus() == stat) {
                bookstatus[i] = book;
                i++;
            }
        }
        return bookstatus;
    }

    /**
     * Displays all books in the library.
     */
    public void displayBooks() {
        System.out.println("Library Books: {");
        for (LibraryBook book : books) {
            System.out.println(book);
        }
        System.out.println("}");
    }
}