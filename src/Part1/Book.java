package Part1;

/**
 * Represents a book with details such as title, author, ISBN, edition, summary, and price.
 */
public class Book {
    private String title;
    private String author;
    private String isbn;
    private BookType type;
    private int edition;
    private String summary;
    private double price;

    /**
     * Constructs a new Book with the specified details.
     *
     * @param title - The title of the book (must be 5-40 characters).
     * @param author The author of the book (must be 5-40 characters).
     * @param isbn The ISBN of the book (must be exactly 10 digits).
     * @param edition The edition of the book (must be at least 1).
     * @param summary The summary of the book (must be 20-150 characters).
     * @param price The price of the book (must be greater than 0).
     * @throws IllegalArgumentException If any of the parameters are invalid.
     */
    public Book(String title, String author, String isbn, int edition, String summary, double price) {
        if (title.length() < 5 || title.length() > 40) 
            throw new IllegalArgumentException("Title must be 5-40 characters");

        if (author.length() <= 5 || author.length() >= 40) 
            throw new IllegalArgumentException("Author must be 5-40 characters");

        if (!isbn.matches("\\d{10}")) 
            throw new IllegalArgumentException("ISBN must be exactly 10 digits");

       

        if (summary.length() <= 20 || summary.length() >= 150) 
            throw new IllegalArgumentException("Summary must be 20-150 characters");

        if (price <= 0) 
            throw new IllegalArgumentException("Price must be greater than 0");

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        if (edition >= 1) {
            this.edition = edition;
        }
        this.summary = summary;
        this.price = price;
    }

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The new title of the book (must be 5-40 characters).
     */
    public void setTitle(String title) {
        if (title.length() >= 5 && title.length() <= 40) {
            this.title = title;
        }
    }

    /**
     * Gets the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author The new author of the book (must be 5-40 characters).
     */
    public void setAuthor(String author) {
        if (author.length() >= 5 && author.length() <= 40) {
            this.author = author;
        }
    }

    /**
     * Gets the ISBN of the book.
     *
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn The new ISBN of the book (must be exactly 10 digits).
     * @throws IllegalArgumentException If the ISBN is invalid.
     */
    public void setIsbn(String isbn) {
        if (isbn != null && isbn.matches("\\d{10}")) {
            this.isbn = isbn;
        } else {
            throw new IllegalArgumentException("ISBN must be exactly 10 digits.");
        }
    }

    /**
     * Gets the type of the book.
     *
     * @return The type of the book.
     */
    public BookType getType() {
        return type;
    }

    /**
     * Sets the type of the book.
     *
     * @param type The new type of the book.
     */
    public void setType(BookType type) {
        this.type = type;
    }

    /**
     * Gets the edition of the book.
     *
     * @return The edition of the book.
     */
    public int getEdition() {
        return edition;
    }

    /**
     * Sets the edition of the book.
     *
     * @param edition The new edition of the book (must be at least 1).
     */
    public void setEdition(int edition) {
        if (edition >= 1) {
            this.edition = edition;
        }
    }

    /**
     * Gets the summary of the book.
     *
     * @return The summary of the book.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary of the book.
     *
     * @param summary The new summary of the book (must be 20-150 characters).
     */
    public void setSummary(String summary) {
        if (summary.length() >= 20 && summary.length() <= 150) {
            this.summary = summary;
        }
    }

    /**
     * Gets the price of the book.
     *
     * @return The price of the book.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the book.
     *
     * @param price The new price of the book (must be greater than 0).
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the book.
     *
     * @return A string containing the book's details.
     */
    @Override
    public String toString() {
        return "Title: " + title + ", " +
               "Author: " + author + ", " +
               "ISBN: " + isbn + ", " +  
               "Edition: " + edition + ", " +
               "Summary: " + summary + ", " +
               String.format("£%.2f", price); 
    }
}