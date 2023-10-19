package solis.erick.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.*;

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
     */
    public Task(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Atributtes
     */
    private String query = "";
    private PreparedStatement preparedStatement;
    private Connection connection;
    private final ObjectMapper objectMapper = new ObjectMapper();

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
            query = "INSERT INTO task (title) VALUES (?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
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
                Task newTask = new Task(resultSet.getInt(1), resultSet.getString(2));
                ObjectNode taskObjectNode = objectMapper.createObjectNode();
                taskObjectNode.put("id", newTask.getId());
                taskObjectNode.put("title", newTask.getTitle());
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
     * @param pTask task in JSON format
     * @return true/false
     */
    public boolean updateTask(String pTask) {
        try {
            JsonNode taskJSON = objectMapper.readTree(pTask);
            int id = taskJSON.get("id").asInt();
            String title = taskJSON.get("title").asText();
            query = "UPDATE task SET title = ? WHERE id = ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(3, id);
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected != 0;
        } catch (Exception ex) {
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
     * @param pTask task in JSON format
     * @return true/false
     */
    public boolean deleteTask(String pTask) {
        try {
            JsonNode taskJSON = objectMapper.readTree(pTask);
            int id = taskJSON.get("id").asInt();
            query = "DELETE FROM task WHERE id = ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected != 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Attributes for database connection
     */
    private final String user = "todo_app_user";
    private final String password = "321";
    private final String urlConnection = "jdbc:sqlserver://DESKTOP-EC894PS\\SQLEXPRESS\\MSSQL:1433;databaseName=db_todo_app;encrypt=false;trustServerCertificate=false";

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
