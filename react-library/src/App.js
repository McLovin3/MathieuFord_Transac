import { useEffect, useState } from 'react';
import './App.css';
import UsersComponent from './components/UsersComponents';

function App() {

  useEffect(() => {
    const getEmployees = async () => {
      const employees = await fetchEmployees();
      setUsers(employees);
    }
    getEmployees();
  }, []);

  const fetchEmployees = async () => {
    const response = await fetch("http://localhost:5000/employees");
    const employees = await response.json();
    return employees;
  }

  const [users, setUsers] = useState([]);

  return (
    <div className="container">
      <h1>Bibliotèque Java town</h1>
      <UsersComponent users={users}></UsersComponent>
    </div >
  );
}


export default App;
