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

    /**
     * We update a task
     *
     * @param pId of thask
     * @param pTitle of task
     * @param pDescription of task
     * @return updateTask()
     */
    public boolean updateTask(int pId, String pTitle, String pDescription) {
        return task.updateTask(pId, pTitle, pDescription);
    }

    /**
     * We check if there is a task list
     *
     * @return existTaskList()
     */
    public boolean existTaskList() {
        return task.existTaskList();
    }

    /**
     * We are looking for a specific task
     *
     * @param pId of task
     * @return searchTask()
     */
    public boolean searchTask(int pId) {
        return task.searchTask(pId);
    }
}
