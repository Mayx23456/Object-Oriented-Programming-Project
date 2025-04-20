package Part1;

/**
 * Represents a library book that can be lent out. Extends the Book class and implements the Lendable interface.
 */
public class LibraryBook extends Book implements Lendable {

    private int id;
    private BookStatus status;
    private String image;
    private int loanCount;
    static int nextId = 1; 

    /**
     * Constructs a new LibraryBook with the specified details.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param isbn The ISBN of the book.
     * @param type The type of the book.
     * @param edition The edition of the book.
     * @param summary The summary of the book.
     * @param price The price of the book.
     * @param coverImage The cover image of the book.
     */
    public LibraryBook(String title, String author, String isbn, BookType type, int edition, String summary, double price, String coverImage) {
        super(title, author, isbn, edition, summary, price);
        this.id = nextId++; 
        this.status = BookStatus.AVAILABLE;
        this.image = coverImage; 
        this.loanCount = 0;
    }

    /**
     * Gets the ID of the book.
     *
     * @return The ID of the book.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the status of the book.
     *
     * @return The status of the book.
     */
    public BookStatus getStatus() {
        return status;
    }

    /**
     * Gets the cover image of the book.
     *
     * @return The cover image of the book.
     */
    public String getImage() {
        return image;
    }

    /**
     * Gets the loan count of the book.
     *
     * @return The number of times the book has been borrowed.
     */
    public int getLoanCount() {
        return loanCount;
    }

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return super.getTitle();
    }

    /**
     * Gets the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return super.getAuthor();
    }

    /**
     * Gets the ISBN of the book.
     *
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return super.getIsbn();
    }

    /**
     * Gets the type of the book.
     *
     * @return The type of the book.
     */
    public BookType getType() {
        return super.getType();
    }

    /**
     * Gets the edition of the book.
     *
     * @return The edition of the book.
     */
    public int getEdition() {
        return super.getEdition();
    }

    /**
     * Gets the summary of the book.
     *
     * @return The summary of the book.
     */
    public String getSummary() {
        return super.getSummary();
    }

    /**
     * Gets the price of the book.
     *
     * @return The price of the book.
     */
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * Sets the status of the book.
     *
     * @param status The new status of the book.
     */
    public void setStatus(BookStatus status) {
        this.status = status;
    }

    /**
     * Sets the cover image of the book.
     *
     * @param image The new cover image of the book.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Sets the loan count of the book.
     *
     * @param loanCount The new loan count of the book.
     */
    public void setLoanCount(int loanCount) {
        this.loanCount = loanCount;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The new title of the book.
     */
    public void setTitle(String title) {
        super.setTitle(title);
    }

    /**
     * Sets the author of the book.
     *
     * @param author The new author of the book.
     */
    public void setAuthor(String author) {
        super.setAuthor(author);
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn The new ISBN of the book.
     */
    public void setIsbn(String isbn) {
        super.setIsbn(isbn);
    }

    /**
     * Sets the type of the book.
     *
     * @param type The new type of the book.
     */
    public void setType(BookType type) {
        super.setType(type);
    }

    /**
     * Sets the edition of the book.
     *
     * @param edition The new edition of the book.
     */
    public void setEdition(int edition) {
        super.setEdition(edition);
    }

    /**
     * Sets the summary of the book.
     *
     * @param summary The new summary of the book.
     */
    public void setSummary(String summary) {
        super.setSummary(summary);
    }

    /**
     * Sets the price of the book.
     *
     * @param price The new price of the book.
     */
    public void setPrice(double price) {
        super.setPrice(price);
    }

    /**
     * Checks out the book if it is available.
     *
     * @return true if the book was successfully checked out, false otherwise.
     */
    @Override
    public boolean checkout() {
        if (status == BookStatus.AVAILABLE) {
            status = BookStatus.ON_LOAN;
            loanCount++;
            return true;
        }
        return false;
    }

    /**
     * Checks in the book if it is on loan.
     *
     * @return true if the book was successfully checked in, false otherwise.
     */
    @Override
    public boolean checkin() {
        if (status == BookStatus.ON_LOAN) {
            status = BookStatus.AVAILABLE;
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the book.
     *
     * @return A string containing the book's details.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", " + super.toString() + ", Status: " + status + 
                ", Image: " + image + ", Times Borrowed: " + loanCount;
    }
}