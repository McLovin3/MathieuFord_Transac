import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
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

//TODO do server requests need to be here?

function App() {
  return (
    <Router>
      <div className="col-8 text-center mx-auto mt-5 border border-2 border-dark bg-info rounded p-3">
        <Link to="/" className="h1 text-decoration-none text-dark">Bibliotèque Java town</Link>
        <Routes>
          <Route path="/" element={<HomeComponent />} />
          <Route path="attendant/:id" element={<AttendantComponent />} />
          <Route path="attendant/:id/addBook" element={<AddBookComponent />} />
          <Route path="attendant/:id/addClient" element={<AddClientComponent />} />
          <Route path="attendant/:id/addCD" element={<AddCDComponent />} />
          <Route path="attendant/:id/addDVD" element={<AddDVDComponent />} />
          <Route path="client/:id" element={<ClientComponent />} />
          <Route path="client/:id/documents" element={<DocumentsComponent />} />
          <Route path="client/:id/borrows" element={<BorrowsComponent />} />
        </Routes>
      </div >
    </Router>
  );
}

export default App;
