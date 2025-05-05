package CA_2;

enum ManagementStaff {
    HEAD_MANAGER("Head Manager"),
    ASSISTANT_MANAGER("Assistant Manager"),
    TEAM_LEAD("Team Lead");

    private final String displayName;

    ManagementStaff(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static void showMenu() {
        for (int i = 0; i < values().length; i++) {
            System.out.println((i + 1) + ". " + values()[i].getDisplayName());
        }
    }

    public static ManagementStaff fromOption(int option) {
        if (option < 1 || option > values().length) {
            throw new IllegalArgumentException("Invalid option.");
        }
        return values()[option - 1];
    }
}