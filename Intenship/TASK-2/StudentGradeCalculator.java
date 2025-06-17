
import java.util.Scanner;

public class StudentGradeCalculator {

    public static String Grade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B+";
        } else if (percentage >= 60) {
            return "B";
        } else if (percentage >= 50) {
            return "C+";
        } else if (percentage >= 40) {
            return "C";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of subjects: ");
        int n = s.nextInt();
        double[] marks = new double[n];
        double totalMarks = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            marks[i] = s.nextDouble();
            totalMarks += marks[i];
        }
        double avgpercentage = totalMarks / n;
        String grade = Grade(avgpercentage);
        System.out.println("\n*************** Result ***************");
        System.out.println("\tTotal Marks: " + totalMarks + "\t\t\t");
        System.out.println("\tGrade: " + grade + "\t\t\t");
        System.out.printf("\tAverage Percentage: " + avgpercentage + "\t\t\t\n");
        s.close();
    }
}
