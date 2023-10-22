import React, { useState } from 'react';
import '../css/EditForm.css';

function EditForm({ task, onSave, onCancel }) {

  const [editedTask, setEditedTask] = useState(task);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditedTask({ ...editedTask, [name]: value });
  };

  const handleEdit = (e) => {
    e.preventDefault();
    onSave(editedTask);
  };

  return (
    <>
      <h1>Editar tarea</h1>
      <div className="task-edit-form">
        <form onSubmit={handleEdit}>
          <div className="input-container">
            <input
              className="input-edit-task"
              type="text"
              placeholder="Editar tarea"
              name="text"
              id="title"
              value={editedTask.text}
              onChange={handleChange} />
          </div>
          <div className="button-container">
            <button className='button-edit-task'>
              Guardar cambios
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