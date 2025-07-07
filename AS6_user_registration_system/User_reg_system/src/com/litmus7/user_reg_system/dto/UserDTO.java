package com.litmus7.user_reg_system.dto;

public class UserDTO {
    private String username;
    private int age;
    private String email;
    private String password;
    
    public UserDTO() {}
    
    public UserDTO(String username, int age, String email, String password) {
        this.username = username;
        this.age = age;
        this.email = email;
        this.password = password;
    }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public void displayDetails() {
        System.out.println("User Details:");
        System.out.println("Username: " + username);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Password: " + "*".repeat(password.length()));
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}

