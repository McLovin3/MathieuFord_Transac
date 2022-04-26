import { useState } from 'react';
import './App.css';
import ButtonComponent from './components/ButtonComponent';
import UsersComponent from './components/UsersComponents';

function App() {
  const clients = [
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
  ];

  const employees = [
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
  ];

  const [users, setUsers] = useState(clients);
  const [isClients, setIsClients] = useState(true);

  const onClick = () => {
    setIsClients(!isClients);
    isClients ? setUsers(employees) : setUsers(clients);
  }

  return (
    <div className="container">
      <h1>Bibliotèque Java town</h1>
      <UsersComponent users={users}></UsersComponent>
      <ButtonComponent color={isClients ? "pink" : "red"} text="Clients" disabled={isClients} onClick={onClick}></ButtonComponent>
      <ButtonComponent color={isClients ? "red" : "pink"} text="Employé" disabled={!isClients} onClick={onClick}></ButtonComponent>
    </div >
  );
}


export default App;
