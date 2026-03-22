package com.dave.springboot;

public class Student {
    private int id;
    private String name;
    private int marks;

    // Constructor có tham số
    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Tự sinh Getter, Setter, toString ở đây nhé thằng lười!
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getMarks() { return marks; }
    public void setMarks(int marks) { this.marks = marks; }
}