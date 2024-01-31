import React, { useState } from "react";
import '../css/Task.css'
import { AiOutlineCloseCircle, AiOutlineEdit } from 'react-icons/ai';
import EditForm from "./EditForm";

function Task({ id, title, description, state, completedTask, deleteTask, updateTask }) {

  const [isEditing, setIsEditing] = useState(false);

  const handleEditClick = () => {
    setIsEditing(true);
  };

  const closeEditForm = () => {
    setIsEditing(false);
  };

  const taskContainerClass = state ? 'task-container completed' : 'task-container';

  return (
    <div className={taskContainerClass}>
      {isEditing ? (
        <div className="modal">
          <div className="modal-content">
            <EditForm task={{ id, title, description }} onSave={closeEditForm} updateTask={updateTask} />
          </div>
          <div className="modal-overlay" onClick={closeEditForm} />
        </div>
      ) : (
        <>
          <div className='task-text' onClick={() => completedTask(id)}>
            {title}
          </div>
          <div className='task-container-icons'>
            <AiOutlineEdit className='task-icon' onClick={handleEditClick} />
          </div>
          <div className='task-container-icons' onClick={() => deleteTask(id)}>
            <AiOutlineCloseCircle className='task-icon' />
          </div>
        </>
      )}
    </div>
  );
}

export default Task;