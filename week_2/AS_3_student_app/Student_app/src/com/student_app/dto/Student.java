package com.student_app.dto;

import java.util.Scanner;

public class Student {
    private String name;
    private int rollNumber;
    private int[] marks = new int[5];

    public void inputDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student name: ");
        name = sc.nextLine();
        System.out.print("Enter roll number: ");
        rollNumber = sc.nextInt();
        System.out.println("Enter marks for 5 subjects:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Subject " + (i+1) + ": ");
            marks[i] = sc.nextInt();
        }
        sc.close();
    }

    public int calculateTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    public double calculateAverage() {
        return calculateTotal() / 5.0;
    }

    public String getGrade() {
        double avg = calculateAverage();
        if (avg >= 90) 
        	return "A";
        else if (avg >= 75) 
        	return "B";
        else if (avg >= 60) 
        	return "C";
        else if (avg >= 50) 
        	return "D";
        else 
        	return "F";
    }

    public void printReportCard() {
        System.out.println("\n--- Report Card ---");
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Total Marks: " + calculateTotal());
        System.out.println("Average Marks: " + calculateAverage());
        System.out.println("Grade: " + getGrade());
    }
}
