// This line says the class is part of the CA_2 package
package CA_2;

// This class represents a manager
public class Manager {

    // The manager's name
    private String name;

    // The manager's level (for example: Team Lead, Head Manager)
    private String level;

    // This is the constructor. It sets the manager's name and level when a new manager is created
    public Manager(String name, String level) {
        this.name = name;
        this.level = level;
    }

    // This method returns the manager's name
    public String getName() {
        return name;
    }

    // This method returns the manager's level
    public String getLevel() {
        return level;
    }
}
