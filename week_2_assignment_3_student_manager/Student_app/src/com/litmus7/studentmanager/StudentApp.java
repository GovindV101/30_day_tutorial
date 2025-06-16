package com.litmus7.studentmanager;

import java.util.Scanner;

import com.litmus7.studentmanager.dto.Student;

/**
 * The StudentApp class provides functionality to input student details,
 * store them, and print their report cards.
 */
public class StudentApp {

    /**
     * Takes input from the user and returns a Student object populated with the entered data.
     *
     * @param scanner The Scanner object used to read input from the console.
     * @return A Student object with populated fields.
     */
    public static Student inputDetails(Scanner scanner) {
        System.out.print("Enter student name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();
        Student student = new Student(name, rollNumber);

        int[] marks = new int[5];
        System.out.println("Enter marks for 5 subjects:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        student.setMarks(marks);
        return student;
    }

    /**
     * Main method that drives the application.
     * Prompts user for number of students, gathers their details, and prints report cards.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many students?: ");
        int n = scanner.nextInt();
        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            students[i] = inputDetails(scanner);
        }

        scanner.close();

        System.out.println("\n--- All Report Cards ---");
        for (Student student : students) {
            student.printReportCard();
        }
    }
}
