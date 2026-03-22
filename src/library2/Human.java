package library2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Human {
    static transient int nextId = 0;
    private transient final int id;
    private transient String forename;
    private transient String surename;
    private transient String name;
    private transient int age;

    public Human(String forename, String surename, int age) {
        this.forename = forename;
        this.surename = surename;
        this.age = age;
        name = surename + " " + forename;
        id = nextId++;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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
