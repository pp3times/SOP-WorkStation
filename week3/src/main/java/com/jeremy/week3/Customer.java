package com.jeremy.week3;

public class Customer {
    private String ID;
    private String name;
    private boolean sex;
    private int age;

    public Customer(){
        this("",null,"Female", 0);
    }
    public Customer(String ID, String n, String s, int a) {
        setID(ID);
        setName(n);
        setSex(s);
        setAge(a);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSex(String sex) {
        boolean state = false;
        String mainSex = "male";
        if (sex.toLowerCase().equals(mainSex)) {
            state = true;
        }
        this.sex = state;
    }

    public boolean getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 0){
            age = 0;
        }
        this.age = age;
    }


}
