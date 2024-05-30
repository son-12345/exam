package student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = JsonUtils.loadStudentsFromFile("students.json");

        // Initialize sample data only if the file is empty or doesn't exist
        if (students == null || students.isEmpty()) {
            students = new ArrayList<>();
            JsonUtils.saveStudentsToFile(students, "students.json");
        }

        int choice;
        do {
            displayMenu();
            choice = getChoice(scanner);

            switch (choice) {
                case 1:
                    StudentManagement.displayAllStudents(students);
                    break;
                case 2:
                    StudentManagement.addStudent(students, scanner);
                    break;
                case 3:
                    StudentManagement.editStudent(students, scanner);
                    break;
                case 4:
                    StudentManagement.searchStudent(students, scanner);
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        JsonUtils.saveStudentsToFile(students, "students.json");
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT =====");
        System.out.println("1. Display all students");
        System.out.println("2. Add new student");
        System.out.println("3. Edit student information");
        System.out.println("4. Search for student");
        System.out.println("0. Exit");
    }

    private static int getChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Discard invalid input
        }
        return scanner.nextInt();
    }
}
