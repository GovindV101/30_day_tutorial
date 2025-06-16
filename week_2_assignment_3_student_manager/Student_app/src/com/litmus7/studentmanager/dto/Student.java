package com.litmus7.studentmanager.dto;

/**
 * Represents a Student with name, roll number, and marks in 5 subjects.
 * Provides functionality to calculate total, average, and grade.
 */
public class Student {
    private String name;
    private int rollNumber;
    private int[] marks = new int[5];
    
    /**
     * Parameterized constructor to initialize student name and roll number.
     *
     * @param name       The student's name.
     * @param rollNumber The student's roll number.
     */
    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }
    /**
     * Sets the marks of the student.
     *
     * @param marks An array of integers representing marks in 5 subjects.
     */
    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    /**
     * Calculates the total marks obtained by the student.
     *
     * @return Total marks.
     */
    private int calculateTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    /**
     * Calculates the average marks obtained by the student.
     *
     * @return Average marks as a double.
     */
    private double calculateAverage() {
        return calculateTotal() / 5.0;
    }

    /**
     * Determines the grade based on average marks.
     *
     * @return Grade as an enum value.
     */
    private Grade getGrade() {
        double avg = calculateAverage();
        if (avg >= 90) return Grade.A;
        else if (avg >= 75) return Grade.B;
        else if (avg >= 60) return Grade.C;
        else if (avg >= 50) return Grade.D;
        else return Grade.F;
    }

    /**
     * Prints the full report card for the student,
     * including name, roll number, total marks, average, and grade.
     */
    public void printReportCard() {
        System.out.println("\n--- Report Card ---");
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Total Marks: " + calculateTotal());
        System.out.println("Average Marks: " + calculateAverage());
        System.out.println("Grade: " + getGrade());
    }
}
