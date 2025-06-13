package com.litmus7.Student.dto;

public class Student {
    private String name;
    private int rollNumber;
    private int[] marks = new int[5];
//	Moving this to Student_app since input details is not required in dto
//    public void inputDetails() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter student name: ");
//        name = scanner.nextLine();
//        System.out.print("Enter roll number: ");
//        rollNumber = scanner.nextInt();
//        System.out.println("Enter marks for 5 subjects:");
//        for (int i = 0; i < 5; i++) {
//            System.out.print("Subject " + (i+1) + ": ");
//            marks[i] = scanner.nextInt();
//        }
//        scanner.close();
//    } 
    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }
// Changed access modifiers of following methods to private
    private int calculateTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    private double calculateAverage() {
        return calculateTotal() / 5.0;
    }

//    public String getGrade() {
//        double avg = calculateAverage();
//        if (avg >= 90) return "A";
//        else if (avg >= 75) return "B";
//        else if (avg >= 60) return "C";
//        else if (avg >= 50) return "D";
//        else return "F";
//    }
    
// Using enum for grade as instructed in connect
    
    private Grade getGrade() {
        double avg = calculateAverage();
        if (avg >= 90) return Grade.A;
        else if (avg >= 75) return Grade.B;
        else if (avg >= 60) return Grade.C;
        else if (avg >= 50) return Grade.D;
        else return Grade.F;
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
