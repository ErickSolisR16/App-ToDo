package solis.erick.Controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import solis.erick.Model.Task;

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
     * @param pTask Task in JSON format
     * @return createTask()
     */
    public boolean createTask(String pTask) {
        return task.createTask(pTask);
    }

    /**
     * Shows the task list
     *
     * @return listTask
     */
    public ArrayNode showListTask() {
        return task.showListTask();
    }

    /**
     * We update a task
     *
     * @param pTask Task in JSON format
     * @return updateTask()
     */
    public boolean updateTask(String pTask) {
        return task.updateTask(pTask);
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

    /**
     * Delete a task
     *
     * @param pTask task in JSON format
     * @return deleteTask()
     */
    public boolean deleteTask(String pTask) {
        return task.deleteTask(pTask);
    }
}