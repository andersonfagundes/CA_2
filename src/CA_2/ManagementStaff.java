// This line says the enum is part of the CA_2 package
package CA_2;

// This is an enum (a list of fixed choices) called ManagementStaff
// It defines different manager roles
enum ManagementStaff {

    // Three types of manager levels
    HEAD_MANAGER("Head Manager"),
    ASSISTANT_MANAGER("Assistant Manager"),
    TEAM_LEAD("Team Lead");

    // This variable stores the name that will be shown to the user
    private final String displayName;

    // Constructor that sets the display name for each type of manager
    ManagementStaff(String displayName) {
        this.displayName = displayName;
    }

    // This method returns the display name
    public String getDisplayName() {
        return displayName;
    }

    // This method shows a menu with all manager options
    public static void showMenu() {
        for (int i = 0; i < values().length; i++) {
            // Shows: 1. Head Manager, 2. Assistant Manager, 3. Team Lead
            System.out.println((i + 1) + ". " + values()[i].getDisplayName());
        }
    }

    // This method returns the manager type based on the number the user chooses
    public static ManagementStaff fromOption(int option) {
        // If the option is invalid (less than 1 or too big), show an error
        if (option < 1 || option > values().length) {
            throw new IllegalArgumentException("Invalid option.");
        }

        // Return the manager based on the number (1 = first, 2 = second, etc.)
        return values()[option - 1];
    }
}
