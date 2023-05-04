package com.DB;

public class Student {
    private final String name;
    private final String sex;
    private final String age;

    public String getName(){return name;}
    public String getSex(){return sex;}
    public String getAge(){return age;}

    public Student(String _name, String _sex, String _age)
    {
        name = _name;
        sex = _sex;
        age = _age;
    }
}
