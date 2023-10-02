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
     * Get Id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Get title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set title
     *
     * @param title of task
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set description
     *
     * @param description of task
     */
    public void setDescription(String description) {
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

    /**
     * Shows the task list
     *
     * @return listTask
     */
    public ArrayList showListTask() {
        return listTask;
    }

    /**
     * Method override toString
     *
     * @return task
     */
    @Override
    public String toString() {
        return "Id: " + id + " titulo: " + title + " descripcion: " + description;
    }

    /**
     * We update a task
     *
     * @param pId of task
     * @param pTitle of task
     * @param pDescription of task
     * @return true/false
     */
    public boolean updateTask(int pId, String pTitle, String pDescription) {
        for (Task task : listTask) {
            if (pId == task.getId()) {
                task.setTitle(pTitle);
                task.setDescription(pDescription);
                return true;
            }
        }
        return false;
    }

    /**
     * We check if there is a task list
     *
     * @return true/false
     */
    public boolean existTaskList() {
        if (listTask.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * We are looking for a specific task
     *
     * @param pId of task
     * @return true/false
     */
    public boolean searchTask(int pId) {
        for (int i = 0; i < listTask.size(); i++) {
            if (pId == listTask.get(i).getId()) {
                return true;
            }
        }
        return false;
    }
}
