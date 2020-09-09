package com.example.lessonsscheduler.model;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class Lesson
{
    private Student student;
    private int hour, minute, length;
    private ArrayList<DayOfWeek> days;
    private String location;

    public Lesson(Student student, int hour, int minute, int length, ArrayList<DayOfWeek> days, String location)
    {
        this.student = student;
        this.hour = hour;
        this.minute = minute;
        this.length = length;
        this.days = days;
        this.location = location;
    }

    public void changeTime(int hour, int minute)
    {
        this.hour = hour;
        this.minute = minute;
    }

    public void changeLength(int length)
    {
        this.length = length;
    }

    public Student getStudent()
    {
        return student;
    }

    public int getHour()
    {
        return hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public int getLength()
    {
        return length;
    }

    public ArrayList<DayOfWeek> getDays()
    {
        return days;
    }

    public int getHash()
    {
        return Math.abs((student.getName() + hour + minute + length + days).hashCode());
    }
}
