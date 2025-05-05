package CA_2;

public class Employee {
    private String name;
    private Department department;
    private Manager manager;
    private String jobTitle;
    private String company;

    public Employee(String name, Department department, Manager manager, String jobTitle, String company) {
        this.name = name;
        this.department = department;
        this.manager = manager;
        this.jobTitle = jobTitle;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public Manager getManager() {
        return manager;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

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
