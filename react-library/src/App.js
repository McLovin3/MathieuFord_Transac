import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import './App.css';
import AddBookComponent from './components/AddBookComponent';
import AddClientComponent from './components/AddClientComponent';
import DocumentsComponent from './components/DocumentsComponent';
import HomeComponent from './components/HomeComponent';
import AttendantComponent from './components/AttendantComponent';
import ClientComponent from './components/ClientComponent';
import BorrowsComponent from './components/BorrowsComponent';

function App() {

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

  return (
    <Router>
      <div className="col-6 text-center mx-auto mt-5 border border-2 border-dark bg-info rounded p-3">
        <Link to="/" className="h1 text-decoration-none text-dark">Bibliotèque Java town</Link>
        <Routes>
          <Route path="/" element={<HomeComponent />} />
          <Route path="attendant/:id" element={<AttendantComponent />} />
          <Route path="attendant/:id/addBook" element={<AddBookComponent postBook={postBook} />} />
          <Route path="attendant/:id/addClient" element={<AddClientComponent postClient={postClient} />} />
          <Route path="client/:id" element={<ClientComponent />} />
          <Route path="client/:id/books" element={<DocumentsComponent />} />
          <Route path="client/:id/borrows" element={<BorrowsComponent />} />
        </Routes>
      </div >
    </Router>
  );
}

export default App;
