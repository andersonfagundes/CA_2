package CA_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TechCompanyApp {
    static Scanner scanner = new Scanner(System.in);
    static List<Employee> employees = new ArrayList<>();
 
    public static void main(String[] args) {
        boolean fileLoaded = false;
        String filename = "";

        while (!fileLoaded) {
            System.out.println("Please enter the filename: ");
            filename = scanner.nextLine();
            fileLoaded = readFile(filename);

            if (!fileLoaded) {
                System.out.println("File not found or could not be read. Try again.\n");
            }
        }
        
        System.out.println("");
        
        System.out.println("Please select an option:");
        for (int i = 0; i < MenuOption.values().length; i++) {
            System.out.println((i + 1) + ". " + MenuOption.values()[i]);
        }

        int choice = Integer.parseInt(scanner.nextLine());

        switch (MenuOption.values()[choice - 1]) {
            case SORT -> {
                System.out.println("SORT Selected ");
                sortEmployees();
                return;
            }
            case SEARCH -> {
                System.out.println("SEARCH Selected");
                searchEmployee();
                return;
            }

            case ADD_RECORDS -> {
                System.out.println("ADD_RECORDS Selected ");
                
                return;
            }
            case EXIT -> {
                System.out.println("Goodbye...");
                return;
            }
        }
    }

    static boolean readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean hasContent = false;
            employees.clear(); // limpa lista para evitar duplicados

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    String name = parts[0].trim();
                    Department dept = new Department(parts[1].trim());
                    Manager mgr = new Manager("", parts[2].trim()); // nome vazio, só usamos o nível
                    String jobTitle = parts[3].trim();
                    String company = parts[4].trim();

                    Employee emp = new Employee(name, dept, mgr, jobTitle, company);
                    employees.add(emp);
                    hasContent = true;
                }
            }

            if (!hasContent) {
                System.out.println("File is empty or has invalid format.");
            } else {
                System.out.println("File read successfully.");
            }

            return hasContent;

        } catch (IOException e) {
            return false;
        }
    }
   
    static void sortEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to sort.");
            return;
        }

        System.out.println("\nChoose a field to sort by:");
        System.out.println("1. Name\n2. Department\n3. Position\n4. Job Title\n5. Company");
        int fieldChoice = Integer.parseInt(scanner.nextLine());

        List<Employee> sorted = mergeSort(new ArrayList<>(employees), fieldChoice);

        System.out.println("\nSorted Employees (first 20):");
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.printf("%-20s | %-20s | %-15s | %-20s | %-20s\n", "Name", "Department", "Position", "Job Title", "Company");
        System.out.println("----------------------------------------------------------------------------------------------");

        for (int i = 0; i < Math.min(20, sorted.size()); i++) {
            System.out.println(sorted.get(i));
        }
    }
    
    static void searchEmployee() {
        if (employees.isEmpty()) {
            System.out.println("No employees to search.");
            return;
        }

        System.out.print("Enter the employee name to search: ");
        String name = scanner.nextLine().trim().toLowerCase();

        // Ordena a lista por nome antes de fazer binary search
        List<Employee> sorted = mergeSort(new ArrayList<>(employees), 1); // 1 = Name
        int index = binarySearch(sorted, name);

        if (index >= 0) {
            Employee e = sorted.get(index);
            System.out.println("\nEmployee found:");
            System.out.println("--------------------------------------------------------------");
            System.out.printf("Name: %s\nDepartment: %s\nManager Level: %s\nJob Title: %s\nCompany: %s\n",
                    e.getName(), e.getDepartment().getName(), e.getManager().getLevel(), e.getJobTitle(), e.getCompany());
        } else {
            System.out.println("Employee not found.");
        }
    }

    
    private static List<Employee> mergeSort(List<Employee> list, int field) {
        if (list.size() <= 1) return list;

        int mid = list.size() / 2;
        List<Employee> left = mergeSort(list.subList(0, mid), field);
        List<Employee> right = mergeSort(list.subList(mid, list.size()), field);

        return merge(left, right, field);
    }

    private static List<Employee> merge(List<Employee> left, List<Employee> right, int field) {
        List<Employee> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            String lValue = getField(left.get(i), field);
            String rValue = getField(right.get(j), field);

            if (lValue.compareToIgnoreCase(rValue) <= 0) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));

        return merged;
    }

    private static String getField(Employee e, int field) {
        return switch (field) {
            case 1 -> e.getName();
            case 2 -> e.getDepartment().getName();
            case 3 -> e.getManager().getLevel();
            case 4 -> e.getJobTitle();
            case 5 -> e.getCompany();
            default -> e.getName();
        };
    }

    
    static int binarySearch(List<Employee> list, String name) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            String midName = list.get(mid).getName().toLowerCase();

            int cmp = midName.compareTo(name);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

}