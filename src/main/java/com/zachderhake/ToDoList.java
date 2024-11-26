package com.zachderhake;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<Task> toDo = new ArrayList<>();
    private List<Task> doing = new ArrayList<>();
    private List<Task> done = new ArrayList<>();

    public void addTask(String status, String name) {
        if (status.equals("Done")){
            done.add(new Task("Done", name));
        } else if (status.equals("Doing")){
            doing.add(new Task("Doing", name));
        } else {
            toDo.add(new Task("toDo", name));
        }
    }

    //needs to be changed
    public List<Task> getToDo() {
        return toDo;
    }

    public List<Task> getDoing() {
        return doing;
    }

    public List<Task> getDone() {
        return done;
    }

}

