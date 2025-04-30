package techcompanyapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TechCompanyApp {
    static Scanner scanner = new Scanner(System.in);

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
                
                return;
            }
            case SEARCH -> {
                System.out.println("SEARCH Selected ");
                
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

        System.out.println("File selected: " + filename);
    }

    static boolean readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean hasContent = false;

            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                hasContent = true;
            }

            if (!hasContent) {
                System.out.println("File is empty.");
            } else {
                System.out.println("File read successfully.");
            }

            return true;

        } catch (IOException e) {
            return false;
        }
    }
}