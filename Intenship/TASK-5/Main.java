import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Roll Number: " + rollNumber + ", Name: " + name + ", Grade: " + grade;
    }
}

public class  Main {
    private ArrayList<Student> students = new ArrayList<>();
    private static final String FILENAME = "students.txt";
    private Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter roll number: ");
        int rollNumber;
        try {
            rollNumber = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number. Please enter a number.");
            return;
        }

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();
        if (grade.isEmpty()) {
            System.out.println("Grade cannot be empty.");
            return;
        }

        students.add(new Student(name, rollNumber, grade));
        System.out.println("Student added successfully.");
    }

    public void removeStudent() {
        System.out.print("Enter roll number to remove: ");
        try {
            int rollToRemove = Integer.parseInt(scanner.nextLine());
            students.removeIf(student -> student.getRollNumber() == rollToRemove);
            System.out.println("Student removed successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number. Please enter a number.");
        }
    }

    public void searchStudent() {
        System.out.print("Enter roll number to search: ");
        try {
            int rollToSearch = Integer.parseInt(scanner.nextLine());
            for (Student student : students) {
                if (student.getRollNumber() == rollToSearch) {
                    System.out.println("Student found: " + student);
                    return;
                }
            }
            System.out.println("Student not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number. Please enter a number.");
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }
        System.out.println("\nAll Students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (Student student : students) {
                writer.println(student.getRollNumber() + "," + student.getName() + "," + student.getGrade());
            }
            System.out.println("Data saved successfully to " + FILENAME);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        students.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int rollNumber = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String grade = parts[2].trim();
                    students.add(new Student(name, rollNumber, grade));
                }
            }
            System.out.println("Data loaded successfully from " + FILENAME);
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    saveToFile();
                    break;
                case 6:
                    loadFromFile();
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        Main system = new Main();
        system.showMenu();
    }
}