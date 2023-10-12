import React from "react";
import '../css/Task.css'
import { AiOutlineCloseCircle } from 'react-icons/ai';

function Task({ text, completed }) {
  return (
    <div className={completed ? 'task-container completed' : 'task-container'}>
      <div className='task-text'>
        {text}
      </div>
      <div className='task-container-icons'>
        <AiOutlineCloseCircle className='task-icon' />
      </div>
    </div>
  );
}

export default Task;