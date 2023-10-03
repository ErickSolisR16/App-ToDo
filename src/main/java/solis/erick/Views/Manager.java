package solis.erick.Views;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.web.bind.annotation.*;
import solis.erick.Controller.TaskController;

import java.util.ArrayList;

@RestController
public class Manager {

    public Manager() {
    }

    private final TaskController controller = new TaskController();

    @PostMapping("/api/createTask")
    public boolean createTask(@RequestBody String pTask) {
        return controller.createTask(pTask);
    }

    @GetMapping("/api/listTask")
    public ArrayNode showListTask() {
        return controller.showListTask();
    }

    @PutMapping("/api/updateTask")
    public boolean updateTask(@RequestBody String pTask) {
        return controller.updateTask(pTask);
    }

    public boolean existTaskList() {
        return controller.existTaskList();
    }

    public boolean searchTask(int pId) {
        return controller.searchTask(pId);
    }

    @DeleteMapping("/api/deleteTask")
    public boolean deleteTask(int pId) {
        return controller.deleteTask(pId);
    }
}
