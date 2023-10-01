package solis.erick.Model;

import java.util.ArrayList;

public class Task {

    /**
     * Atributtes
     */
    private int id = 0;
    private String title, description;

    /**
     * Default constructor
     */
    public Task() {
    }

    /**
     * Constructor that receives all parameters
     *
     * @param id of task
     * @param title of task
     * @param description of task
     */
    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /**
     * Atributtes
     */
    private final ArrayList<Task> listTask = new ArrayList<>();

    /**
     * Creation of new task
     *
     * @param pTitle of task
     * @param pDescription of task
     * @return true
     */
    public boolean createTask(String pTitle, String pDescription) {
        Task newTask = new Task(id, pTitle, pDescription);
        listTask.add(newTask);
        id++;
        return true;
    }
}
