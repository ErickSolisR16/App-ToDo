import React, { useState } from "react";
import '../css/Form.css';
import { v4 as uuidv4 } from 'uuid';

function Form(props) {

  const [input, setInput] = useState();

  const handleChange = event => {
    setInput(event.target.value);
  };
  
  const handleShipping = event => {
    event.preventDefault();
    const newTask = {
      id: uuidv4(),
      text: input,
      completed: false
    };
    props.onSubmit(newTask);
  };

  return (
    <form 
    className='task-form' 
    onSubmit={handleShipping}>
      <input 
      className='input-task' 
      type='text'
      placeholder='Escribe una nueva tarea'
      name='text'
      onChange={handleChange} />
      <button className='button-task'>
        Agregar tarea
      </button>
    </form>
  );
}

export default Form;