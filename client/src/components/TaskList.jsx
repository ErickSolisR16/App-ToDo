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

  const deleteTask = id => {
    const updatedTasks = tasks.filter(task => task.id !== id);
    setTasks(updatedTasks);
  };

  const completedTask = id => {
    const updatedTasks = tasks.map(task => {
      if (task.id === id) {
        task.completed = !task.completed;
      }
      return task;
    });
    setTasks(updatedTasks);
  };

  /**
   * Shows the tasks registered when loading the application
   */
  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/listTask');
        if (!response.ok) {
          throw new Error('Error al obtener las tareas');
        }
        const data = await response.json();
        console.log(data);
        setTasks(data);
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchTasks();
  });

  return (
    <>
      <Form onSubmit={createTask} />
      <div className='task-list-container'>
        {tasks.map(task => (
            <Task 
              key={task.id}
              id={task.id}
              text={task.title}
              completed={task.completed}
              deleteTask={deleteTask}
              completedTask={completedTask}
            />
          ))}
      </div>
    </>
  );
}

export default TaskList;