package com.zachderhake;

public class Task {

    private String status; 
    private String name; 

    // Default constructor (needed for JSON parsing)
    public Task() {}

    // Constructor with parameters
    public Task(String status, String name) {
        this.status = status;
        this.name = name;
    }
    
    public String getStatus() {
        return status; 
    }

    public String getName() {
        return name;
    }

    public void setStatus(String newStatus) {
        status = newStatus;
    }

    public void setName(String newName) {
        name = newName;
    }
}
