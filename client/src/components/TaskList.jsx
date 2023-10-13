import React from "react";
import Form from './Form';
import '../css/TaskList.css';

function TaskList() {
  return (
    <>
      <Form />
      <div className='task-list-container'>
        LISTA DE TAREAS
      </div>
    </>
  );
}

export default TaskList;