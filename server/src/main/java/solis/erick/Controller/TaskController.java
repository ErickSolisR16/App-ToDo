package solis.erick.Controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.web.bind.annotation.*;
import solis.erick.Model.Task;

@RestController
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
    @PostMapping("/api/createTask")
    public boolean createTask(@RequestBody String pTask) {
        return task.createTask(pTask);
    }

    /**
     * Shows the task list
     *
     * @return listTask
     */
    @GetMapping("/api/listTask")
    public ArrayNode showListTask() {
        return task.showListTask();
    }

    /**
     * We update a task
     *
     * @param pTask Task in JSON format
     * @return updateTask()
     */
    @PutMapping("/api/updateTask")
    public boolean updateTask(@RequestBody String pTask) {
        return task.updateTask(pTask);
    }

    /**
     * Delete a task
     *
     * @param pTask task in JSON format
     * @return deleteTask()
     */
    @DeleteMapping("/api/deleteTask")
    public boolean deleteTask(@RequestBody String pTask) {
        return task.deleteTask(pTask);
    }

    /**
     * Update task status
     *
     * @param pTask in JSON format
     * @return updateStatusTask()
     */
    @PutMapping("/api/updateStatusTask")
    public boolean updateStatusTask(String pTask) {
        return task.updateStatusTask(pTask);
    }
}
