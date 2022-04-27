import { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
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
    <Router>
      <div className="container">
        <h1>Bibliotèque Java town</h1>
        <Routes>
          <Route path="addBook" element={<AddBook postBook={postBook} />} />
        </Routes>
      </div >
    </Router>
  );
}


export default App;
