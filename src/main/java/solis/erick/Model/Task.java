package solis.erick.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.*;
import java.util.ArrayList;

public class Task {

    /**
     * Atributtes
     */
    private int id;
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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Atributtes
     */
    private final ArrayList<Task> listTask = new ArrayList<>();
    private String query = "";
    private PreparedStatement preparedStatement;
    private Connection connection;
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Creation of new task
     *
     * @param pTask task in JSON format
     * @return true/false
     */
    public boolean createTask(String pTask) {
        try {
            JsonNode taskJSON = objectMapper.readTree(pTask);
            String title = taskJSON.get("title").asText();
            String description = taskJSON.get("description").asText();
            query = "INSERT INTO task (title, description) VALUES (?, ?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            int rowAffetec = preparedStatement.executeUpdate();
            return rowAffetec != 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Attribute
     */
    private ResultSet resultSet;

    /**
     * Shows the task list
     *
     * @return listTask
     */
    public ArrayNode showListTask() {
        query = "SELECT * FROM task";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            ArrayNode listTask = objectMapper.createArrayNode();
            while (resultSet.next()) {
                Task newTask = new Task(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                ObjectNode taskObjectNode = objectMapper.createObjectNode();
                taskObjectNode.put("id", newTask.getId());
                taskObjectNode.put("title", newTask.getTitle());
                taskObjectNode.put("description", newTask.getDescription());
                listTask.add(taskObjectNode);
            }
            return listTask;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
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
        query = "UPDATE task SET title = ?, description = ? WHERE id = ?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pTitle);
            preparedStatement.setString(2, pDescription);
            preparedStatement.setInt(3, pId);
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected != 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * We check if there is a task list
     *
     * @return true/false
     */
    public boolean existTaskList() {
        query = "SELECT COUNT(*) FROM task";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int rowCount = resultSet.getInt(1);
                return rowCount != 0;
            }
            return false;
        } catch (SQLException ex) {
         ex.printStackTrace();
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
        query = "SELECT id FROM task WHERE id = ?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pId);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a task
     *
     * @param pId of task
     * @return true/false
     */
    public boolean deleteTask(int pId) {
        query = "DELETE FROM task WHERE id = ?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pId);
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected != 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Attributes for database connection
     */
    private final String user = "user_todo_app";
    private final String password = "321";
    private final String urlConnection = "jdbc:sqlserver://DESKTOP-EC894PS\\SQLEXPRESS\\MSSQL:1433;databaseName=todo_app_db;encrypt=false;trustServerCertificate=false";

    /**
     * Connection to the database
     *
     * @return connection
     * @throws SQLException Database exception
     */
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(urlConnection, user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al conectarse a la base de datos");
            ex.printStackTrace();
        }
        return connection;
    }
}
