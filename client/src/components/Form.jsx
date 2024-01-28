import React, { useState } from "react";
import '../css/Form.css';
import { v4 as uuidv4 } from 'uuid';

function Form(props) {

  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const handleTitleChange = (event) => {
    setTitle(event.target.value);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleShipping = (event) => {
    event.preventDefault();
    const newTask = {
      title: title,
      description: description,
    };
    props.onSubmit(newTask);
    document.getElementById('title').value = '';
    document.getElementById('description').value = '';
  };

  return (
    <form
      className='task-form'
      onSubmit={handleShipping}>
      <input
        className='input-task'
        type='text'
        placeholder='Escribe una nueva tarea'
        id='title'
        onChange={handleTitleChange} />

      <input 
        className='input-task'
        type='text'
        placeholder='Agrega una descripcion'
        id='description'
        onChange={handleDescriptionChange} />

      <button className='button-task'>
        Agregar tarea
      </button>
    </form>
  );
}

export default Form;