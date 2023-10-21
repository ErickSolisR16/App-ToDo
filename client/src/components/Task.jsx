import React from "react";
import '../css/Task.css'
import { AiOutlineCloseCircle } from 'react-icons/ai';

function Task({ id, text, state, completedTask, deleteTask }) {

  const taskContainerClass = state ? 'task-container completed' : 'task-container';

  return (
    <div className={taskContainerClass}>
      <div className='task-text'
      onClick={() => completedTask(id)}>
        {text}
      </div>
      <div className='task-container-icons'
      onClick={() => deleteTask(id)}>
        <AiOutlineCloseCircle className='task-icon' />
      </div>
    </div>
  );
}

export default Task;