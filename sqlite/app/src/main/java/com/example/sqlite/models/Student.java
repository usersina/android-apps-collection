package com.example.sqlite.models;

public class Student {
    Integer id;
    String firstname;
    String lastname;
    String classroom;

    public Student(){}

    public Student(String firstname, String lastname, String classroom) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.classroom = classroom;
    }

    public Student(Integer id, String firstname, String lastname, String classroom) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.classroom = classroom;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getClassroom() {
        return classroom;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", classroom='" + classroom + '\'' + '}';
    }
}
