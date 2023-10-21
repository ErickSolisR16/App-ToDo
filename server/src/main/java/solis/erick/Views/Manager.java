package solis.erick.Views;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.web.bind.annotation.*;
import solis.erick.Controller.TaskController;

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

    @DeleteMapping("/api/deleteTask")
    public boolean deleteTask(@RequestBody String pTask) {
        return controller.deleteTask(pTask);
    }

    @PutMapping("/api/updateStatusTask")
    public boolean updateStatusTask(@RequestBody String pTask) {
        return controller.updateStatusTask(pTask);
    }
}
