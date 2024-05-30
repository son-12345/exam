package student;

import java.util.List;
import java.util.Scanner;

public class StudentManagement {

    public static void displayAllStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            for (Student.Subject subject : student.getSubjects()) {
                System.out.print("Subject: " + subject.getName() + ", Scores: ");
                for (int score : subject.getScores()) {
                    System.out.print(score + " ");
                }
                System.out.println();
            }
        }
    }

    public static void addStudent(List<Student> students, Scanner scanner) {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        Student student = new Student(id, name);

        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter subject name: ");
            String subjectName = scanner.nextLine();
            System.out.print("Enter number of scores for " + subjectName + ": ");
            int numScores = scanner.nextInt();
            int[] scores = new int[numScores];
            for (int j = 0; j < numScores; j++) {
                System.out.print("Enter score " + (j + 1) + ": ");
                scores[j] = scanner.nextInt();
            }
            scanner.nextLine(); // Consume newline
            Student.Subject subject = new Student.Subject(subjectName, scores);
            student.getSubjects().add(subject);
        }

        students.add(student);
        System.out.println("Student added successfully.");
    }

    public static void editStudent(List<Student> students, Scanner scanner) {
        System.out.print("Enter student ID to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Student student : students) {
            if (student.getId() == id) {
                System.out.print("Enter new name for the student: ");
                String newName = scanner.nextLine();
                student.setName(newName);

                System.out.print("Enter number of subjects to update: ");
                int numSubjects = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                for (int i = 0; i < numSubjects; i++) {
                    System.out.print("Enter subject name to update: ");
                    String subjectName = scanner.nextLine();
                    for (Student.Subject subject : student.getSubjects()) {
                        if (subject.getName().equals(subjectName)) {
                            System.out.print("Enter new number of scores: ");
                            int numScores = scanner.nextInt();
                            int[] newScores = new int[numScores];
                            for (int j = 0; j < numScores; j++) {
                                System.out.print("Enter score " + (j + 1) + ": ");
                                newScores[j] = scanner.nextInt();
                            }
                            scanner.nextLine(); // Consume newline
                            subject.setScores(newScores);
                            break;
                        }
                    }
                }

                System.out.println("Student information updated successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public static void searchStudent(List<Student> students, Scanner scanner) {
        System.out.print("Enter student ID or name to search: ");
        String keyword = scanner.nextLine();

        for (Student student : students) {
            if (String.valueOf(student.getId()).equals(keyword) || student.getName().equalsIgnoreCase(keyword)) {
                System.out.println("ID: " + student.getId());
                System.out.println("Name: " + student.getName());
                for (Student.Subject subject : student.getSubjects()) {
                    System.out.print("Subject: " + subject.getName() + ", Scores: ");
                    for (int score : subject.getScores()) {
                        System.out.print(score + " ");
                    }
                    System.out.println();
                }
                return;
            }
        }

        System.out.println("Student not found.");
    }
}
