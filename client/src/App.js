import './App.css';
import Task from './components/Task';

function App() {
  return (
    <div className="App">
      <div className='task-list'>
        <h1>Mis Tareas</h1>
        <Task text='Prueba 1'/>
      </div>
    </div>
  );
}

export default App;
