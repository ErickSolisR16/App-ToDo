import React from "react";
import '../css/Form.css';

function Form(props) {
  return (
    <form className='task-form'>
      <input 
      className='input-task' 
      type='text'
      placeholder='Escribe una nueva tarea'
      name='text' />
      <button className='button-task'>
        Agregar tarea
      </button>
    </form>
  );
}

export default Form;