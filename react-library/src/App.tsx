import { useState } from 'react';
import './App.css';
import Button from './components/Button';

function App() {
  let isClients = true;

  const [clients, setClients] = useState(
    [
      {
        id: 1,
        name: 'Homer',
      },
      {
        id: 2,
        name: 'Bart',
      },
      {
        id: 3,
        name: 'Maggie',
      },
    ]
  )

  const [employees, setEmployees] = useState(
    [
      {
        id: 1,
        name: 'Fry',
      },
      {
        id: 2,
        name: 'Leela',
      },
      {
        id: 3,
        name: 'Bender',
      },
    ]
  )

  return (
    <body>
      <h1>Bibliothèque Java Town</h1>
      <Button text={'YAY BUTTON'} onClick={() => isClients = !isClients}></Button>
    </body>
  );
}

export default App;
