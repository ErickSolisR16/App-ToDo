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
    private int id, state;
    private String title, description;

    /**
     * Default constructor
     */
    public Task() {
    }

    /**
     * Constructor that receives all parameters
     *
     * @param id            of task
     * @param title         of task
     * @param description   of task
     * @param state         of task
     */
    public Task(int id, String title, String description, int state) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.state = state;
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

    public int getState() {
        return state;
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
            String description = taskJSON.get("description").asText();
            query = "INSERT INTO task (title, description, state) VALUES (?, ?, ?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, 0);
            int rowAffetec = preparedStatement.executeUpdate();
            connection.close();
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
                Task newTask = new Task(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(4), resultSet.getInt(3));
                ObjectNode taskObjectNode = objectMapper.createObjectNode();
                taskObjectNode.put("id", newTask.getId());
                taskObjectNode.put("title", newTask.getTitle());
                taskObjectNode.put("description", newTask.getDescription());
                taskObjectNode.put("state", newTask.getState());
                listTask.add(taskObjectNode);
            }
            connection.close();
            return listTask;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
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
            String description = taskJSON.get("description").asText();
            query = "UPDATE task SET title = ?, description = ? WHERE id = ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, id);
            int rowAffected = preparedStatement.executeUpdate();
            connection.close();
            return rowAffected != 0;
        } catch (Exception ex) {
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
            connection.close();
            return rowAffected != 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Update task state
     *
     * @param pTask task in JSON format
     * @return true/false
     */
    public boolean updateStatusTask(String pTask) {
        try {
            JsonNode taskJSON = objectMapper.readTree(pTask);
            int id = taskJSON.get("id").asInt();
            int currentState = searchTask(id);
            if (currentState != -1) {
                int newState = (currentState == 1) ? 0 : 1;
                query = "UPDATE task SET state = ? WHERE id = ?";
                connection = getConnection();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, newState);
                preparedStatement.setInt(2, id);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected != 0;
            }
            connection.close();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Search the task in the database
     *
     * @param pId identify from the task
     * @return currentState/-1
     */
    public int searchTask(int pId) {
        try {
            query = "SELECT state FROM task WHERE id = ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pId);
            resultSet = preparedStatement.executeQuery();
            int currentState = -1;
            if (resultSet.next()) {
                currentState = resultSet.getInt("state");
            }
            connection.close();
            return currentState;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * Connection to the database
     *
     * @return connection
     * @throws SQLException Database exception
     */
    public Connection getConnection() throws SQLException {
        try {
            String urlConnection = System.getenv("urlConnection");
            String user = System.getenv("user");
            String password = System.getenv("password");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(urlConnection, user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al conectarse a la base de datos");
            ex.printStackTrace();
        }
        return connection;
    }
}
