package solis.erick.Views;

import solis.erick.Controller.TaskController;

import java.util.ArrayList;

public class Manager {

    public Manager() {
    }

    private final TaskController controller = new TaskController();

    public boolean createTask(String pTitle, String pDescription) {
        return controller.createTask(pTitle, pDescription);
    }

    public ArrayList showListTask() {
        return controller.showListTask();
    }

    public boolean updateTask(int pId, String pTitle, String pDescription) {
        return controller.updateTask(pId, pTitle, pDescription);
    }

    public boolean existTaskList() {
        return controller.existTaskList();
    }

    public boolean searchTask(int pId) {
        return controller.searchTask(pId);
    }

    public boolean deleteTask(int pId) {
        return controller.deleteTask(pId);
    }
}
