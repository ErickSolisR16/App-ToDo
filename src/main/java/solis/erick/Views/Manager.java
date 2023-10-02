package solis.erick.Views;

import solis.erick.Controller.TaskController;

import java.util.ArrayList;

public class Manager {

    public Manager() {
    }

    private TaskController controller = new TaskController();

    public boolean createTask(String pTitle, String pDescription) {
        return controller.createTask(pTitle, pDescription);
    }

    public ArrayList showListTask() {
        ArrayList listTask = controller.showListTask();
        return listTask;
    }
}
