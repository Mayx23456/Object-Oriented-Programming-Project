package Part1;
import java.util.Scanner;

/**
 * Represents a menu for user interaction.
 */
public class Menu {
    private String[] items;
    private String title;
    private Scanner input;

    /**
     * Constructs a new Menu with the specified title and menu items.
     *
     * @param title The title of the menu.
     * @param data  The list of menu items.
     */
    public Menu(String title, String[] data) {
        this.title = title;
        this.items = data;
        this.input = new Scanner(System.in);
    }

    /**
     * Displays the menu with the title and options.
     */
    private void display() {
        System.out.println(title);
        for (int count = 0; count < title.length(); count++) {
            System.out.print("+");
        }
        System.out.println();
        for (int option = 1; option <= items.length; option++) {
            System.out.println(option + ". " + items[option - 1]);
        }
        System.out.println();
    }

    /**
     * Prompts the user to enter their choice and returns the selected option.
     *
     * @return The user's selected option.
     */
    public int getUserChoice() {
        display();
        System.out.print("Enter Selection: ");
        int value = input.nextInt();
        return value;
    }
}