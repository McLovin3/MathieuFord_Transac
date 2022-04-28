import { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import AddBookComponent from './components/AddBookComponent';
import AddClientComponent from './components/AddClientComponent';
import BooksComponent from './components/BooksComponent';
import HomeComponent from './components/HomeComponent';

function App() {

  const [selectedUser, setSelectedUser] = useState();

  const postBook = async (book) => {
    await fetch("http://localhost:5000/books",
      {
        method: "POST",
        headers: {
          "content-type": "application/json"
        },
        body: JSON.stringify(book)
      });
  }

  const postClient = async (client) => {
    await fetch("http://localhost:5000/clients",
      {
        method: "POST",
        headers: {
          "content-type": "application/json"
        },
        body: JSON.stringify(client)
      });
  }


  const setUser = (user) => {
    setSelectedUser(user);
  }

  return (
    <Router>
      <div className="col-6 text-center mx-auto mt-5 border border-2 border-dark bg-info rounded p-3">
        <h1>Bibliotèque Java town</h1>
        <h2>{selectedUser == null ? "" : selectedUser.name}</h2>
        <Routes>
          <Route path="/" element={<HomeComponent onClick={setUser} />} />
          <Route path="addBook" element={<AddBookComponent postBook={postBook} />} />
          <Route path="addClient" element={<AddClientComponent postClient={postClient} />} />
          <Route path="books" element={<BooksComponent />} />
        </Routes>
      </div >
    </Router>
  );
}


export default App;
