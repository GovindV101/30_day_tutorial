package com.litmus7.Student_manager;

import java.util.Scanner;
import com.litmus7.Student.dto.Student;

public class StudentApp {

    public static Student inputDetails(Scanner scanner) {
        Student student = new Student();

        System.out.print("Enter student name: ");
        scanner.nextLine(); 
        student.setName(scanner.nextLine());

        System.out.print("Enter roll number: ");
        student.setRollNumber(scanner.nextInt());

        int[] marks = new int[5];
        System.out.println("Enter marks for 5 subjects:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        student.setMarks(marks);
        return student;
    }

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
