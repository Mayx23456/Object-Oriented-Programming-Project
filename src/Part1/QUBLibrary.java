package Part1;

import java.util.Scanner;

/**
 * Represents the main application for managing a library system.
 */
public class QUBLibrary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        boolean running = true;

        // Initialise predefined books
        initializeLibrary(library);

        // Define menu items
        String[] menuItems = {
            "List All Books",
            "List Books by Status",
            "Add a Book",
            "Remove a Book",
            "Borrow a Book",
            "Return a Book",
            "Display Ranked List",
            "Exit"
        };

        // Create the menu
        Menu menu = new Menu("QUBLibrary", menuItems);

        while (running) {
            int choice = menu.getUserChoice();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.println("Enter book status (AVAILABLE, ON_LOAN, WITHDRAW): ");
                    String statusInput = scanner.nextLine().toUpperCase();
                    try {
                        BookStatus status = BookStatus.valueOf(statusInput);
                        LibraryBook[] books = library.list(status);
                        for (LibraryBook book : books) {
                            if (book != null) System.out.println(book);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status.");
                    }
                    break;
                case 3:
                    library.collectDetails();
                    break;
                case 4:
                    System.out.print("Enter the ID of the book to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();
                    if (library.remove(removeId)) {
                        System.out.println("Book removed successfully.");
                    } else {
                        System.out.println("Failed to remove book. It may be on loan or not exist.");
                    }
                    break;
                case 5:
                    System.out.print("Enter the ID of the book to borrow: ");
                    int borrowId = scanner.nextInt();
                    scanner.nextLine();
                    if (library.borrowBook(borrowId)) {
                        System.out.println("Book borrowed successfully.");
                    } else {
                        System.out.println("Failed to borrow book. It may not be available.");
                    }
                    break;
                case 6:
                    System.out.print("Enter the ID of the book to return: ");
                    int returnId = scanner.nextInt();
                    scanner.nextLine();
                    if (library.returnBook(returnId)) {
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Failed to return book. It may not be on loan.");
                    }
                    break;
                case 7:
                    LibraryBook[] rankedBooks = library.mostPopular();
                    System.out.println("Most Popular Books:");
                    for (LibraryBook book : rankedBooks) {
                        if (book != null) {
                            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Borrowed: " + book.getLoanCount() + " times");
                        }
                    }
                    break;
                case 8:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Initialises the library with a set of predefined books.
     *
     * @param library The library to initialise.
     */
    private static void initializeLibrary(Library library) {
        System.out.println("Initializing Library...");

        LibraryBook book1 = new LibraryBook("To Kill a Mockingbird", "Harper Lee", "1234567890", BookType.FICTION, 3, 
                                            "A novel about racial injustice in the Deep South.", 10.99, "mockingbird.jpg");

        LibraryBook book2 = new LibraryBook("Sapiens: A Brief History", "Yuval Noah Harari", "0987654321", BookType.NON_FICTION, 2, 
                                            "A thought-provoking book that explores human history and evolution.", 14.99, "sapiens.jpg");

        LibraryBook book3 = new LibraryBook("The Catcher in the Rye", "J.D. Salinger", "1122334455", BookType.FICTION, 2, 
                                            "A story about teenage alienation and rebellion in 1950s America.", 8.99, "catcher_rye.jpg");

        LibraryBook book4 = new LibraryBook("The Lean Startup", "Eric Ries", "6677889900", BookType.REFERENCE, 3, 
                                            "A guide to innovative business practices and entrepreneurship.", 12.99, "lean_startup.jpg");

        LibraryBook book5 = new LibraryBook("Astrophysics for People", "Neil deGrasse Tyson", "5544332211", BookType.REFERENCE, 2, 
                                            "A concise yet engaging introduction to astrophysics and space science.", 11.50, "astrophysics.jpg");

        // Add books to library
        library.add(book1);
        library.add(book2);
        library.add(book3);
        library.add(book4);
        library.add(book5);

        // Display total books after initialisation
        System.out.println("Total books after initialization: " + library.list().length);
    }
}