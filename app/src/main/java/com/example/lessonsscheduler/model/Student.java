package com.example.lessonsscheduler.model;

public class Student
{
    private String firstName, lastName;
    private String school;
    private int grade;
    private String contact;

    public Student(String firstName, String lastName, int grade, String contact)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.contact = contact;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getName()
    {
        return firstName + ' ' + lastName;
    }

    public int getGrade()
    {
        return grade;
    }

    public String getContact()
    {
        return contact;
    }
}
