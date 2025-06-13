package com.sa.school;
import com.student_app.dto.*;
import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many students?: ");
        int n = sc.nextInt();
        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for student " + (i+1) + ":");
            students[i] = new Student();
            students[i].inputDetails();
        }
        sc.close();
        System.out.println("\n--- All Report Cards ---");
        for (Student student : students) {
            student.printReportCard();
        }
    }
}
