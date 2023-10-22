import React, { useState } from 'react';
import '../css/EditForm.css';

function EditForm({ task, onSave, onCancel, updateTask }) {

  const [editedTask, setEditedTask] = useState({id: task.id, title: task.text });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditedTask({ ...editedTask, [name]: value });
  };

  const handleEdit = async (e) => {
    e.preventDefault();
    updateTask(editedTask);
    onSave();
  };

  return (
    <>
      <h1>Editar tarea</h1>
      <div className="task-edit-form">
        <form>
          <div className="input-container">
            <input
              className="input-edit-task"
              type="text"
              placeholder="Editar tarea"
              name="title"
              id="title"
              value={editedTask.title}
              onChange={handleChange}
            />
          </div>
          <div className="button-container">
            <button className='button-edit-task' type="button" onClick={handleEdit}>
              Guardar tarea
            </button>
            <button className='button-cancel-task' onClick={onCancel}>
              Cancelar
            </button>
          </div>
        </form>
      </div>
    </>
  );
}

export default EditForm;