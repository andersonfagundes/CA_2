// This line says the class is part of the CA_2 package
package CA_2;

// This class represents an employee
public class Employee {

    // The employee's name
    private String name;

    // The department where the employee works
    private Department department;

    // The manager responsible for the employee
    private Manager manager;

    // The employee's job title
    private String jobTitle;

    // The name of the company
    private String company;

    // This is the constructor. It sets all the information when we create a new employee
    public Employee(String name, Department department, Manager manager, String jobTitle, String company) {
        this.name = name;
        this.department = department;
        this.manager = manager;
        this.jobTitle = jobTitle;
        this.company = company;
    }

    // This method returns the employee's name
    public String getName() {
        return name;
    }

    // This method returns the employee's department
    public Department getDepartment() {
        return department;
    }

    // This method returns the employee's manager
    public Manager getManager() {
        return manager;
    }

    // This method returns the employee's job title
    public String getJobTitle() {
        return jobTitle;
    }

    // This method returns the company name
    public String getCompany() {
        return company;
    }

    // This method returns all employee information in a formatted string
    @Override
    public String toString() {
        return String.format("%-20s | %-20s | %-15s | %-20s | %-20s",
                name,
                department.getName(),
                manager.getLevel(),
                jobTitle,
                company
        );
    }
}
