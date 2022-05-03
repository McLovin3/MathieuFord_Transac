import { BrowserRouter as Router, Link, Navigate, Route, Routes } from 'react-router-dom';
import './App.css';
import AddBookComponent from './components/AddBookComponent';
import AddClientComponent from './components/AddClientComponent';
import DocumentsComponent from './components/DocumentsComponent';
import HomeComponent from './components/HomeComponent';
import AttendantComponent from './components/AttendantComponent';
import ClientComponent from './components/ClientComponent';
import BorrowsComponent from './components/BorrowsComponent';
import AddCDComponent from './components/AddCDComponent';
import AddDVDComponent from './components/AddDVDComponent';

function App() {
  return (
    <Router>
      <div className="col-10 text-center mx-auto mt-5 border border-2 border-dark bg-info rounded p-3">
        <Link to="/" className="h1 text-decoration-none text-dark">Bibliotèque Java town</Link>
        <Routes>
          <Route exact path="/" element={<HomeComponent />} />
          <Route path="attendant/:id" element={<AttendantComponent />} />
          <Route path="attendant/:id/addBook" element={<AddBookComponent />} />
          <Route path="attendant/:id/addClient" element={<AddClientComponent />} />
          <Route path="attendant/:id/addCD" element={<AddCDComponent />} />
          <Route path="attendant/:id/addDVD" element={<AddDVDComponent />} />
          <Route path="client/:id" element={<ClientComponent />} />
          <Route path="client/:id/documents" element={<DocumentsComponent />} />
          <Route path="client/:id/borrows" element={<BorrowsComponent />} />
          <Route path="notFound" element={<h2>Page existe pas</h2>} />
          <Route path="*" element={<Navigate replace to={"/notFound"} />} />
        </Routes>
      </div >
    </Router>
  );
}

export default App;
