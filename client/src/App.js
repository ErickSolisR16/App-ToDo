import './App.css';
import TaskList from './components/TaskList';

function App() {
  return (
    <div className="App">
      <div className='task-list'>
        <h1>Mis Tareas</h1>
        <TaskList />
      </div>
    </div>
  );
}

export default App;
