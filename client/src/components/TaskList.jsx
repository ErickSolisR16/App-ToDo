import React, { useState, useEffect } from "react";
import Form from './Form';
import Task from './Task';
import '../css/TaskList.css';

function TaskList() {

  const [tasks, setTasks] = useState([]);

  const addTask = task => {
    if (task.text.trim()) {
      task.text = task.text.trim();
      const updatedTasks = [task, ...tasks];
      setTasks(updatedTasks);
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
  }, []);

  return (
    <>
      <Form onSubmit={addTask} />
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