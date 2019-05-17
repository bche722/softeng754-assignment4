package nz.ac.auckland.softeng754_assignment4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reviewer {

    private String username;
    private int id;
    private int count;

    public void increment(){
        this.count = this.count + 1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
