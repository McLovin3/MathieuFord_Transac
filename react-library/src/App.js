import { useEffect, useState } from 'react';
import './App.css';
import AddBook from './components/AddBook';

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

  const postBook = (book) => {
    fetch("http://localhost:5000/books",
      {
        method: "POST",
        headers: {
          "content-type": "application/json"
        },
        body: JSON.stringify(book)
      });
  }

  const [users, setUsers] = useState([]);

  return (
    <div className="container">
      <h1>Bibliotèque Java town</h1>
      <AddBook postBook={postBook}></AddBook>
    </div >
  );
}


export default App;
