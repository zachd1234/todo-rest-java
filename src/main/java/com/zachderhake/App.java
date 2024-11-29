package com.zachderhake;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.FileRenderer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


public class App {

    private static ToDoList theToDoList = new ToDoList();
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/public";
                staticFileConfig.location = Location.CLASSPATH;
            });

            config.fileRenderer((filePath, model, ctx) -> {
                try {
                    // Read the file content as a String and return it for rendering
                    return Files.readString(Paths.get(App.class.getResource(filePath).toURI()));
                } catch (Exception e) {
                    throw new RuntimeException("File rendering error: " + e.getMessage(), e);
                }
            });
        }).start(7777);

        // Route to render index.html at the root URL
        app.get("/", ctx -> {
            ctx.render("/public/index.html");
        });

            
            //app.get("/", AppController::Test);
        //app.get("/{special}", AppController::specialTest);
        app.post("/tasks", App::addTask);
        app.get("/todo", App::getToDo);
        app.get("/doing", App::getDoing);
        app.get("/done", App::getDone);
        app.delete("/tasks/{name}", App::removeTask);
        app.put("/tasks", App::updateTask);
    }

    private static void addTask(Context context) {
        try {
            // Parse the JSON body into a Task object
            Task task = context.bodyAsClass(Task.class);
    
            // Validate input
            if (task.getName() == null || task.getName().isEmpty() ||
                task.getStatus() == null || task.getStatus().isEmpty()) {
                context.status(400).result("Name and Status are required");
                return;
            }
    
            // Add the task
            theToDoList.addTask(task.getStatus(), task.getName());
    
            // Send success response
            context.status(200).result(task.getName() + " task added with status " + task.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            context.status(500).result("Server error: " + e.getMessage());
        }
       }
    private static void removeTask(Context context) {
        String name = context.pathParam("name");
    

        for (int i = 0; i < theToDoList.getToDo().size(); i++) {
            if(name.equals(theToDoList.getToDo().get(i).getName())){
                theToDoList.getToDo().remove(i);
                context.status(200).result("Task removed from To Do: " + name);
                return; 
            }
        }

        for (int i = 0; i < theToDoList.getDoing().size(); i++) {
            if(name.equals(theToDoList.getDoing().get(i).getName())){
                context.status(200).result("Task removed from Doing: " + name);
                theToDoList.getDoing().remove(i);
                return; 
            }
        }

        for (int i = 0; i < theToDoList.getDone().size(); i++) {
            if(name.equals(theToDoList.getDone().get(i).getName())){
                theToDoList.getDone().remove(i);
                context.status(200).result("Task removed from Done: " + name);
                return; 
            }
        }

        context.status(404).result("Task not found: " + name);
    
    }   

    private static void updateTask(Context context) {
        Map<String, Object> requestData = context.bodyAsClass(Map.class);

        String originalName = (String) requestData.get("originalName");
        String newName = (String) requestData.get("name");
        String status = (String) requestData.get("status");

        
        boolean taskFound = false; 
        
        for (int i = 0; i < theToDoList.getToDo().size(); i++) {
            if (originalName.equals(theToDoList.getToDo().get(i).getName())) {
                theToDoList.getToDo().remove(i);
                taskFound = true;
                break;
            }
        }
    
        // Search and remove task from Doing list
        if (!taskFound) {
            for (int i = 0; i < theToDoList.getDoing().size(); i++) {
                if (originalName.equals(theToDoList.getDoing().get(i).getName())) {
                    theToDoList.getDoing().remove(i);
                    taskFound = true;
                    break;
                }
            }
        }
    
        // Search and remove task from Done list
        if (!taskFound) {
            for (int i = 0; i < theToDoList.getDone().size(); i++) {
                if (originalName.equals(theToDoList.getDone().get(i).getName())) {
                    theToDoList.getDone().remove(i);
                    taskFound = true;
                    break;
                }
            }
        }
    
        // If task not found, return a 404 error
        if (!taskFound) {
            context.status(404).result("Task not found: " + originalName);
            return;
        }
    
    

        theToDoList.addTask(status, newName);
        context.status(200).result("Task removed and added");


    }
 
    private static void getToDo(Context context) {
        context.json(theToDoList.getToDo());
    }

    private static void getDoing(Context context) {
        context.json(theToDoList.getDoing());
    }

    private static void getDone(Context context) {
        context.json(theToDoList.getDone());
    }


}