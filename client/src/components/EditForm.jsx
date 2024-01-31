import React, { useState } from 'react';
import '../css/EditForm.css';

function EditForm({ task, onSave, onCancel, updateTask }) {

  const [editedTitle, setEditedTitle] = useState(task.title);
  const [editedDescription, setEditedDescription] = useState(task.description);

  const handleTitleChange = (e) => {
    setEditedTitle(e.target.value);
  };

  const handleDescriptionChange = (e) => {
    setEditedDescription(e.target.value);
  };

  const handleSave = () => {
    const editedTask = {
      id: task.id,
      title: editedTitle,
      description: editedDescription,
    };
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
              value={editedTitle}
              onChange={handleTitleChange}
            />
            <br />
            <input
              className="input-edit-task"
              type="text"
              placeholder="Editar descripcion"
              name="description"
              id="description"
              value={editedDescription}
              onChange={handleDescriptionChange}
            />
          </div>
          <div className="button-container">
            <button className='button-edit-task' type="button" onClick={handleSave}>
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