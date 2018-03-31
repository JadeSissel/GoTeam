package com.example.pc044400.logindemo;


public class Person {

    private int id;
    private String name;
    private String address;
    private String team;
    private String phone;
    private String description;
    private String my_skills;
    private String college;
    private int years_of_service;


    public Person()
    {

    }
    public Person(int id,String name,String address)
    {
        this.id=id;
        this.name=name;
        this.address=address;
    }




    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public int getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }

}
