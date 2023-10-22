import React, { useState, useEffect } from "react";
import Form from './Form';
import Task from './Task';
import '../css/TaskList.css';

function TaskList() {

  const [tasks, setTasks] = useState([]);

  /**
   * Creating a new task
   * 
   * @param {*} task 
   */
  const createTask = async task => {
    try {
      const response = await fetch('http://localhost:8080/api/createTask', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(task),
      });
      if (!response.ok) {
        throw new Error('Error al crear la tarea');
      }
      const updatedTasks = await fetchTasks();
      setTasks(updatedTasks);
    } catch (error) {
      console.error('Error al crear la tarea en el servidor ==> ', error);
    }
  };

  /**
   * Shows registered tasks when creating a new task
   * 
   * @returns array
   */
  const fetchTasks = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/listTask');
      if (!response.ok) {
        throw new Error('Error al obtener las tareas');
      }
      const data = await response.json();
      return data;
    } catch (error) {
      console.error('Error:', error);
      return [];
    }
  };

  /**
   * Deleting a task
   * 
   * @param {int} id of task
   * @returns true/false
   */
  const deleteTask = async id => {
    try {
      const taskToDelete = tasks.find(task => task.id === id);
      if (!taskToDelete) {
        console.error('No se encontro la tarea con el ID: ', id);
        return;
      }
      const response = await fetch('http://localhost:8080/api/deleteTask', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(taskToDelete),
      });
      if (!response.ok) {
        throw new Error('Error al eliminar la tarea');
      }
      setTasks(prevTasks => prevTasks.filter(task => task.id !== id));
    } catch (error) {
      console.error('Error ==> ', error);
    }
  };

  /**
   * We mark a task as completed
   * 
   * @param {int} id 
   * @returns true/false/task
   */
  const completedTask = async id => {
    try {
      const taskToUpdateState = tasks.find(task => task.id === id);
      if (!taskToUpdateState) {
        console.error('No se encontro la tarea con el ID: ', id);
        return;
      }
      const response = await fetch('http://localhost:8080/api/updateStatusTask', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(taskToUpdateState),
      });
      if (!response.ok) {
        throw new Error('Error al completar la tarea');
      }
      const updatedTasks = tasks.map(task => {
        if (task.id === id) {
          task.state = !task.state;
        }
        return task;
      });
      setTasks(updatedTasks);
    } catch (error) {
      console.error('Error ==> ', error);
    }
  };

  /**
   * Task update
   * 
   * @param {*} updatedTask 
   */
  const updateTask = async (updatedTask) => {
    try {
      const response = await fetch('http://localhost:8080/api/updateTask', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedTask),
      });
      if (response.status === 200) {
        const updatedTasks = tasks.map((task) =>
          task.id === updatedTask.id ? updatedTask : task
        );
        setTasks(updatedTasks);
      } else {
        console.error('Error al actualizar la tarea en el servidor');
      }
    } catch (error) {
      console.error('Error ==> ', error);
    }
  };

  /**
   * Shows the tasks registered when loading the application
   */
  useEffect(() => {
    const loadTasks = async () => {
      const data = await fetchTasks();
      setTasks(data);
    };
    loadTasks();
  }, []);

  return (
    <>
      <Form onSubmit={createTask} />
      <div className='task-list-container'>
        {tasks.map(task => (
          <Task
            key={task.id}
            id={task.id}
            text={task.title}
            state={task.state}
            deleteTask={deleteTask}
            completedTask={completedTask}
            updateTask={updateTask}
          />
        ))}
      </div>
    </>
  );
}

export default TaskList;