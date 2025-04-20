package Part2;

import Part1.Library;
import Part1.LibraryBook;
import Part1.BookType;
import Part1.BookStatus;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import console.Console;


/**
 * QUBLibraryUpdated - Enhanced console-based library management system
 * Uses the Console class for input/output with visual enhancements
 */
public class QUBLibraryupdated {
    private static Console console;
    private static Library library;
    private static final String IMAGES_PATH = "/Users/mayukhbanerjee/Documents/OOP_Project/Assessment2/src/Images";
    
    // UI Constants
    private static final Color BG_COLOR = new Color(114, 47, 55); // Wine Red background
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color ERROR_COLOR = Color.RED;
    private static final Color SUCCESS_COLOR = new Color(0, 204, 102); // Green
    private static final Color HEADER_COLOR = new Color(255, 215, 0); // Gold colour for header

    // Fonts
    private static final Font HEADER_FONT = new Font("Times New Roman", Font.BOLD, 24); // Bold Times New Roman for headings
    private static final Font MENU_FONT = new Font("Times New Roman", Font.PLAIN, 20);
    private static final Font TEXT_FONT = new Font("Times New Roman", Font.PLAIN, 18);
    
    /**
     * Main method to initialise the library and console, and display the main menu.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialise library
        library = new Library();
        
        // Initialise console
        console = new Console(true);
        console.setSize(1000, 600);
        console.setVisible(true);
        
        // Check if any books were initialised
        LibraryBook[] initialBooks = library.list();
        // If no books were initialised, initialise them manually
        if (initialBooks.length == 0) {
            initializeLibrary();
        }
        
        setupConsoleUI();
        displayMainMenu();
    }

    /**
     * Displays the main menu of the library management system.
     * Allows the user to choose from various options such as listing books, adding a book, borrowing a book, etc.
     */
    private static void displayMainMenu() {
        boolean exit = false;
        while(!exit) {
            // Clear the console
            console.clear();
            // Display header
            console.setFont(HEADER_FONT);
            console.setColour(HEADER_COLOR);
            
            console.println("𝓠𝓤𝓑 𝓛𝓲𝓫𝓻𝓪𝓻𝔂 𝓜𝓪𝓷𝓪𝓰𝓮𝓶𝓮𝓷𝓽 𝓢𝔂𝓼𝓽𝓮𝓶");
            
            // Display menu options
            console.setFont(MENU_FONT);
            console.setColour(TEXT_COLOR);
            console.println("\n1. List All Books");
            console.println("2. List Books by Status");
            console.println("3. Add a New Book");
            console.println("4. Remove a Book");
            console.println("5. Borrow a Book");
            console.println("6. Return a Book");
            console.println("7. Display Ranked List");
            console.println("8. Exit");
            
            // Get user choice
            console.println("\nEnter your choice (1-8): ");
            String user_input = console.readLn();
            
            try {
                int ch = Integer.parseInt(user_input.trim());
                
                switch(ch) {
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    listBooksByStatus();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    removeBook();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    displayRankedList();
                    break;
                case 8:
                    exit = true;
                    console.setColour(SUCCESS_COLOR);
                    console.println("Goodbye!");

                    // Load and display the "bye" image
                    String byeImagePath = IMAGES_PATH + "/bye.jpg"; 
                    File byeImageFile = new File(byeImagePath);
                    if (byeImageFile.exists()) {
                        try {
                            ImageIcon icon = new ImageIcon(byeImagePath); // Load the image
                            Image image = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Resize the image
                            ImageIcon resizedIcon = new ImageIcon(image);

                            // Display the image 
                            console.println(resizedIcon);
                        } catch (Exception e) {
                            console.setColour(ERROR_COLOR);
                            console.println("Error loading image: " + e.getMessage());
                        }
                    } else {
                        console.setColour(ERROR_COLOR);
                        console.println("Image file not found at path: " + byeImagePath);
                    }

                    console.readLn();
                    console.dispose();
                    break;
                default:
                    console.setColour(ERROR_COLOR);
                    console.println("Invalid Choice, Please try again!");
                    enter();
                }
                
            } catch (Exception ex) {
                console.setColour(ERROR_COLOR);
                console.println("Invalid input. Please enter a number.");
                enter();
            }
        }
    }

    /**
     * Displays a ranked list of books based on their popularity.
     */
    private static void displayRankedList() {
        console.clear();
        console.setFont(HEADER_FONT);
        console.setColour(HEADER_COLOR);
        console.println("----- Ranked List of Books by Popularity -----");
        console.setFont(TEXT_FONT);
        console.setColour(TEXT_COLOR);

        LibraryBook[] book = library.mostPopular();

        if (book.length == 0) {
            console.println("No Books in the library");
        } else {
            for (int i = 0; i < book.length; i++) {
                LibraryBook books = book[i];
                console.setColour(new Color(255, 215, 0)); // Gold colour for rank
                console.print("Rank " + (i + 1) + ": ");
                console.setColour(TEXT_COLOR);
                displayBookInfo(books, i + 1);

                // Debug: Print the loan count of each book
                console.println("Loan count: " + books.getLoanCount());
            }
        }
        enter();
    }

    /**
     * Allows the user to return a borrowed book.
     */
    private static void returnBook() {
        console.clear();
        console.setFont(HEADER_FONT);
        console.setColour(HEADER_COLOR);
        console.println("----- Return a Book -----");
        console.setFont(TEXT_FONT);
        console.setColour(TEXT_COLOR);
        
        try {
            console.print("Enter the ID of the book to return: ");
            int id = Integer.parseInt(console.readLn().trim());
            
            if (library.returnBook(id)) {
                console.setColour(SUCCESS_COLOR);
                console.println("Book returned successfully!");
            } else {
                console.setColour(ERROR_COLOR);
                console.println("Book not found or was not borrowed.");
            }
        } catch (Exception ex) {
            console.setColour(ERROR_COLOR);
            console.println("Error: " + ex.getMessage());
        }
        enter();
    }

    /**
     * Allows the user to borrow a book.
     */
    /**
     * Allows the user to borrow a book by first displaying the list of available books
     * and then prompting the user to input the ID of the book they want to borrow.
     */
    private static void borrowBook() {
        // Display all books before selection
        displayAllBooksForSelection();

        console.setFont(HEADER_FONT);
        console.setColour(HEADER_COLOR);
        console.println("----- Borrow a Book -----");
        console.setFont(TEXT_FONT);
        console.setColour(TEXT_COLOR);

        try {
            console.print("\nEnter the ID of the book you want to borrow: ");
            int id = Integer.parseInt(console.readLn().trim());

            // Attempt to borrow the book
            if (library.borrowBook(id)) {
                console.setColour(SUCCESS_COLOR);
                console.println("Book borrowed successfully!");
            } else {
                console.setColour(ERROR_COLOR);
                console.println("Book not found or not available for borrowing.");
            }
        } catch (NumberFormatException ex) {
            console.setColour(ERROR_COLOR);
            console.println("Invalid input. Please enter a valid book ID.");
        } catch (Exception ex) {
            console.setColour(ERROR_COLOR);
            console.println("Error: " + ex.getMessage());
        }
        enter(); // Wait for user to press Enter before returning to the main menu
    }

    /**
     * Allows the user to remove a book from the library.
     */
    private static void removeBook() {
        // Display all books before selection
        displayAllBooksForSelection();

        console.setFont(HEADER_FONT);
        console.setColour(HEADER_COLOR);
        console.println("----- Remove a Book -----");
        console.setFont(TEXT_FONT);
        console.setColour(TEXT_COLOR);

        try {
            console.print("\nEnter the ID of the book to remove: ");
            int id = Integer.parseInt(console.readLn().trim());

            if (library.remove(id)) {
                console.setColour(SUCCESS_COLOR);
                console.println("Book removed successfully!");
            } else {
                console.setColour(ERROR_COLOR);
                console.println("Book not found or could not be removed (might be on loan).");
            }
        } catch (Exception ex) {
            console.setColour(ERROR_COLOR);
            console.println("Error: " + ex.getMessage());
        }
        enter(); 
    }
    
    /**
     * Allows the user to add a new book to the library.
     */
    private static void addBook() {
        // Display all books before selection
        displayAllBooksForSelection();

        console.setFont(HEADER_FONT);
        console.setColour(HEADER_COLOR);
        console.println("----- Add a New Book -----");
        console.setFont(TEXT_FONT);
        console.setColour(TEXT_COLOR);

        try {
            // Get book details
            console.print("\nEnter title (5-40 chars): ");
            String title = console.readLn();

            console.print("Enter author (5-40 chars): ");
            String author = console.readLn();

            console.print("Enter ISBN (10 digits): ");
            String isbn = console.readLn();

            // Get book type
            console.println("\nSelect book type:");
            console.println("1. Fiction");
            console.println("2. Non-Fiction");
            console.println("3. Reference");
            console.print("Enter choice (1-3): ");

            int typeChoice = Integer.parseInt(console.readLn().trim());
            BookType type;
            switch (typeChoice) {
                case 1:
                    type = BookType.FICTION;
                    break;
                case 2:
                    type = BookType.NON_FICTION;
                    break;
                case 3:
                    type = BookType.REFERENCE;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid book type");
            }

            console.print("Enter edition (≥1): ");
            int edition = Integer.parseInt(console.readLn().trim());

            console.print("Enter summary (20-150 chars): ");
            String summary = console.readLn();

            console.print("Enter price (>0): ");
            double price = Double.parseDouble(console.readLn().trim());

            console.print("Enter cover image filename: ");
            String coverImage = console.readLn();

            // Create and add the book - the Book constructor will handle validation
            LibraryBook newBook = new LibraryBook(title, author, isbn, type, edition, summary, price, coverImage);
            if (library.add(newBook)) {
                console.setColour(SUCCESS_COLOR);
                console.println("Book added successfully!");
            } else {
                console.setColour(ERROR_COLOR);
                console.println("Failed to add book.");
            }
        } catch (IllegalArgumentException ex) {
            console.setColour(ERROR_COLOR);
            console.println("Error: " + ex.getMessage());
        }
        enter(); 
    }
    
    /**
     * Displays all books in the library with their details.
     */
    
    private static void displayAllBooksForSelection() {
        console.clear();
        console.setFont(HEADER_FONT);
        console.setColour(HEADER_COLOR);
        console.println("----- All Books in the Library -----");
        console.setFont(TEXT_FONT);
        console.setColour(TEXT_COLOR);

        LibraryBook[] books = library.list();
        if (books.length == 0) {
            console.println("No books available in the library.");
        } else {
            for (int i = 0; i < books.length; i++) {
                LibraryBook book = books[i];
                displayBookInfo(book, i + 1);
            }
        }
        // Removed the enter() call to keep the user on the same page
    }
    
    /**
     * Lists all books in the library that match a specific status (e.g., AVAILABLE, ON_LOAN, WITHDRAWN).
     */
    private static void listBooksByStatus() {
        console.clear();
        console.setFont(HEADER_FONT);
        console.setColour(HEADER_COLOR);
        console.println("\n----- List Books by Status -----\n");
        console.setFont(MENU_FONT);
        console.setColour(TEXT_COLOR);
        
        console.println("\n1. Available");
        console.println("2. On Loan");
        console.println("3. Withdrawn");
        console.print("\nEnter status choice (1-3): ");
        
        try {
            int ch = Integer.parseInt(console.readLn().trim());
            BookStatus status;
            
            switch(ch) {
            case 1:
                status = BookStatus.AVAILABLE;
                break;
            case 2:
                status = BookStatus.ON_LOAN;
                break;
            case 3:
                status = BookStatus.WITHDRAW;
                break;
            default:
                console.setColour(ERROR_COLOR);
                console.println("Invalid option. Returning to main menu.");
                enter();
                return;
            }
            
            console.clear();
            console.setFont(HEADER_FONT);
            console.setColour(HEADER_COLOR);
            console.println("----- Books with Status: " + status + " -----");
            console.setFont(TEXT_FONT);
            console.setColour(TEXT_COLOR);
            
            LibraryBook[] books = library.list(status);
            if (books.length == 0) {
                console.println("No books with status: " + status);
            } else {
                for (int i = 0; i < books.length; i++) {
                    LibraryBook book = books[i];
                    displayBookInfo(book, i + 1);
                }
            }
            
        } catch (Exception ex) {
            console.setColour(ERROR_COLOR);
            console.println("Invalid input. Please enter a number.");
        }
        enter();
    }

    /**
     * Lists all books in the library.
     */
    private static void listAllBooks() {
        console.clear();
        console.setFont(HEADER_FONT);
        console.setColour(HEADER_COLOR);
        console.println("\n----- All Books -----\n");
        console.setFont(MENU_FONT);
        console.setColour(TEXT_COLOR);
        
        LibraryBook[] book = library.list();
        if (book.length == 0) {
            console.println("No Books in the library");
        } else {
            for (int i = 0; i < book.length; i++) {
                LibraryBook books = book[i];
                displayBookInfo(books, i + 1);
            }
        }
        enter();    
    }

    /**
     * Displays detailed information about a specific book.
     * 
     * @param books The book to display information about
     * @param i The index of the book in the list
     */
    private static void displayBookInfo(LibraryBook books, int i) {
        console.setColour(new Color(255, 165, 0)); // Orange for book number
        console.println("\n" + i + ". " + books.getTitle() + " by " + books.getAuthor());
        console.setColour(TEXT_COLOR);
        console.println("   ID: " + books.getId() + ", Type: " + books.getType() + ", Edition: " + books.getEdition());
        console.println("   Price: £" + String.format("%.2f", books.getPrice()) + ", Status: " + books.getStatus());
        console.println("   Summary: " + books.getSummary());

        // Display book cover image if available
        String imagePath = books.getImage();
        if (imagePath != null && !imagePath.isEmpty()) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                try {
                    ImageIcon icon = new ImageIcon(imagePath);
                    // Resize the image to fit
                    Image image = icon.getImage().getScaledInstance(200, 250, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(image);

                   
                    console.println(resizedIcon);
                } catch (Exception e) {
                    console.setColour(ERROR_COLOR);
                    console.println("   [Error loading image: " + e.getMessage() + "]");
                    console.setColour(TEXT_COLOR);
                }
            } else {
                console.setColour(ERROR_COLOR);
                console.println("   [Image file not found at path: " + imagePath + "]");
                console.setColour(TEXT_COLOR);
            }
        } else {
            console.setColour(ERROR_COLOR);
            console.println("   [No image path provided for this book.]");
            console.setColour(TEXT_COLOR);
        }
    }

    /**
     * Waits for the user to press Enter before returning to the main menu.
     */
    private static void enter() {
        console.setColour(TEXT_COLOR);
        console.println("\nPress Enter to return to Main Menu...");
        console.readLn();
    }

    /**
     * Sets up the console UI with a welcome message and initial styling.
     */
    private static void setupConsoleUI() {
        console.setBgColour(BG_COLOR); // Set background to Wine Red
        console.setColour(HEADER_COLOR); // Set header text colour to Gold
        console.setFont(HEADER_FONT);  // Bold Times New Roman for headings

        console.println("========================================");
        console.println("     𝓦𝓔𝓛𝓒𝓞𝓜𝓔 𝓣𝓞 𝓠𝓤𝓑 𝓛𝓘𝓑𝓡𝓐𝓡𝓨     ");
        console.println("========================================");

        console.setColour(TEXT_COLOR); 
        console.setFont(TEXT_FONT); // Set normal text font
        console.println("--------------------------------------");
        console.println(" Press Enter to continue...           ");
        console.println("--------------------------------------");

        console.readLn();
    }
    
    /**
     * Initialises the library with a set of predefined books.
     */
    private static void initializeLibrary() {
        // Fiction books
        LibraryBook book1 = new LibraryBook("Nineteen Eighty Four", "George Orwell", "1122334455", BookType.FICTION, 3, "A dystopian novel about a totalitarian regime that suppresses truth and freedom.", 10.99, IMAGES_PATH + "/1984.jpg");
        library.add(book1);

        LibraryBook book2 = new LibraryBook("Pride and Prejudice", "Jane Austen", "2233445566", BookType.FICTION, 1, "A classic romance novel exploring love, class, and society in 19th-century England.", 8.99, IMAGES_PATH + "/Pride-and-Prejudice.jpg");
        library.add(book2);

        // Non-fiction books
        LibraryBook book3 = new LibraryBook("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "3344556677", BookType.NON_FICTION, 4, "A book exploring the history and impact of Homo sapiens on the world.", 14.99, IMAGES_PATH + "/Sapiens.jpg");
        library.add(book3);

        LibraryBook book4 = new LibraryBook("Educated", "Tara Westover", "4455667788", BookType.NON_FICTION, 3, "A memoir of a woman who grew up in a strict and abusive household but pursued education against all odds.", 12.99, IMAGES_PATH + "/Educated.jpg");
        library.add(book4);

        // Reference book
        LibraryBook book5 = new LibraryBook("The Feynman Lectures on Physics", "Richard P. Feynman", "5566778899", BookType.REFERENCE, 2, "A comprehensive introduction to physics by Nobel laureate Richard Feynman.", 49.99, IMAGES_PATH + "/Feynman-lectures-on-physics.jpg");
        library.add(book5);
    }
}