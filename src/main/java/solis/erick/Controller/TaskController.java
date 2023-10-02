package solis.erick.Controller;

import solis.erick.Model.Task;

import java.util.ArrayList;

public class TaskController {

    /**
     * Default constructor
     */
    public TaskController() {
    }

    /**
     * Attribute
     */
    private final Task task = new Task();

    /**
     * Create a new task
     *
     * @param pTitle of task
     * @param pDescription of task
     * @return createTask()
     */
    public boolean createTask(String pTitle, String pDescription) {
        return task.createTask(pTitle, pDescription);
    }

    /**
     * Shows the task list
     *
     * @return listTask
     */
    public ArrayList showListTask() {
        ArrayList listTask = task.showListTask();
        return listTask;
    }
}
