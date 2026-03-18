package library;

public abstract class Human {
    private String forename;
    private String surename;
    private int age;

    public Human(String forename, String surename, int age) {
        this.forename = forename;
        this.surename = surename;
        this.age = age;
    }

    public String getForename() {
        return forename;
    }

    public String getSurename() {
        return surename;
    }

    public int getAge() {
        return age;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }
    
    public void setSurename(String surename) {
        this.surename = surename;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return forename + " " + surename + ", age: " + age;
    }
}
