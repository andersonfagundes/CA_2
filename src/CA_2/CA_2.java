package CA_2;

import java.io.*;
import java.util.*;

public class CA_2 {
    static Scanner scanner = new Scanner(System.in);
    static List<Employee> employees = new ArrayList<>();
    static String filename;

    public static void main(String[] args) {
        // 1. Solicita o nome do arquivo até ser encontrado ou criado
        while (true) {
            System.out.println("Enter filename to load (Applicants_Form.txt): ");
            filename = scanner.nextLine().trim();

            File file = new File(filename);
            if (file.exists()) {
                readFile(filename);
                break;
            } else {
                System.out.println("Invalid file. Try again.");
                System.out.println("");
            }
        }

        // 2. Exibe o menu principal
        while (true) {
            System.out.println("\nChoose an option:");
            for (int i = 0; i < MenuOption.values().length; i++) {
                System.out.println((i + 1) + ". " + MenuOption.values()[i]);
            }

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                MenuOption option = MenuOption.values()[choice - 1];

                switch (option) {
                    case SORT -> sortEmployees();
                    case SEARCH -> searchEmployee();
                    case ADD_RECORDS -> showAddRecordMenu();
                    case EXIT -> {
                        System.out.println("Exiting program. Goodbye!");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void readFile(String filename) {
        employees.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Employee e = new Employee(
                            parts[0].trim(),
                            new Department(parts[1].trim()),
                            new Manager("", parts[2].trim()),
                            parts[3].trim(),
                            parts[4].trim()
                    );
                    employees.add(e);
                }
            }
            System.out.println("");
            System.out.println("File loaded with " + employees.size() + " employees.");
        } catch (IOException e) {
            System.out.println("Failed to read file.");
        }
    }

    static void showAddRecordMenu() {
        System.out.println("\nAdd Record Menu:");
        for (int i = 0; i < AddRecordOption.values().length; i++) {
            System.out.println((i + 1) + ". " + AddRecordOption.values()[i]);
        }

        try {
            int option = Integer.parseInt(scanner.nextLine());
            AddRecordOption selected = AddRecordOption.values()[option - 1];

            switch (selected) {
                case ADD_EMPLOYEE -> addEmployeeManually();
                case GENERATE_RANDOM_EMPLOYEE -> generateRandomEmployee();
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Returning to main menu.");
        }
    }

    static void addEmployeeManually() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Department: ");
        String dept = scanner.nextLine().trim();

        System.out.print("Manager Level: ");
        String level = scanner.nextLine().trim();

        System.out.print("Job Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Company: ");
        String company = scanner.nextLine().trim();

        Employee e = new Employee(name, new Department(dept), new Manager("", level), title, company);
        employees.add(e);
        saveToFile(e);
        System.out.println("Employee added.");
    }

    static void generateRandomEmployee() {
        String[] names = {"John Doe", "Jane Smith", "Alice Johnson", "Bob Lee", "Maria Clark"};
        String[] departments = {"IT", "HR", "Marketing", "Finance", "Sales"};
        String[] levels = {"Junior", "Mid", "Senior", "Lead"};
        String[] titles = {"Software Engineer", "Project Manager", "Analyst", "HR Specialist"};
        String[] companies = {"TechCorp", "BizSoft", "InnovateX", "DevSolutions"};

        String name = getRandom(names);
        String dept = getRandom(departments);
        String level = getRandom(levels);
        String title = getRandom(titles);
        String company = getRandom(companies);

        Employee e = new Employee(name, new Department(dept), new Manager("", level), title, company);
        employees.add(e);
        saveToFile(e);

        System.out.println("\nRandom Employee Generated:");
        System.out.printf("Name: %s\nDepartment: %s\nManager Level: %s\nJob Title: %s\nCompany: %s\n",
                name, dept, level, title, company);
    }

    static String getRandom(String[] array) {
        return array[new Random().nextInt(array.length)];
    }

    static void saveToFile(Employee e) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(String.format("%s, %s, %s, %s, %s%n",
                    e.getName(),
                    e.getDepartment().getName(),
                    e.getManager().getLevel(),
                    e.getJobTitle(),
                    e.getCompany()));
        } catch (IOException ex) {
            System.out.println("Error writing to file.");
        }
    }

    static void sortEmployees() {
    if (employees.isEmpty()) {
        System.out.println("No employees to sort.");
        return;
    }
    
    List<Employee> sorted = mergeSort(new ArrayList<>(employees), 1); // 1 = Name

    System.out.println("");
    System.out.println("Sorting "+ sorted.size() + " employees by name: ");
    System.out.println("");
    
    System.out.printf("%-20s %-23s %-16s %-22s %-15s\n", "Name", "Department", "Level", "Job Title", "Company");
    System.out.println("--------------------------------------------------------------------------------------------------------------");

    for (Employee e : sorted) {
        System.out.println(e);
    }
}

    static List<Employee> mergeSort(List<Employee> list, int field) {
        if (list.size() <= 1) return list;
        int mid = list.size() / 2;
        List<Employee> left = mergeSort(list.subList(0, mid), field);
        List<Employee> right = mergeSort(list.subList(mid, list.size()), field);
        return merge(left, right, field);
    }

    static List<Employee> merge(List<Employee> left, List<Employee> right, int field) {
        List<Employee> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            String lVal = getField(left.get(i), field);
            String rVal = getField(right.get(j), field);
            if (lVal.compareToIgnoreCase(rVal) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }

    static String getField(Employee e, int field) {
        return switch (field) {
            case 1 -> e.getName();
            case 2 -> e.getDepartment().getName();
            case 3 -> e.getManager().getLevel();
            case 4 -> e.getJobTitle();
            case 5 -> e.getCompany();
            default -> "";
        };
    }

    static void searchEmployee() {
        if (employees.isEmpty()) {
            System.out.println("No employees loaded.");
            return;
        }

        System.out.print("Enter employee name to search: ");
        String query = scanner.nextLine().trim().toLowerCase();

        List<Employee> sorted = mergeSort(new ArrayList<>(employees), 1); // Sort by name
        int index = binarySearch(sorted, query);

        if (index >= 0) {
            System.out.println("Employee found: \n");
            System.out.printf("%-20s %-23s %-16s %-22s %-15s\n", "Name", "Department", "Level", "Job Title", "Company");
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            Employee e = sorted.get(index);
            System.out.println(e);
        } else {
            System.out.println("Employee not found.");
        }
    }

    static int binarySearch(List<Employee> list, String name) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            String midName = list.get(mid).getName().toLowerCase();
            int cmp = midName.compareTo(name);
            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}
