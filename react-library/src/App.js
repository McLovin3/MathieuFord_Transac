import { BrowserRouter as Router, Link, Navigate, Route, Routes } from 'react-router-dom';
import './App.css';
import HomeComponent from './pages/HomeComponent';
import AttendantComponent from './pages/AttendantComponent';
import ClientComponent from './pages/ClientComponent';


function App() {
  return (
    <Router>
      <div className="col-10 text-center mx-auto mt-5 border border-2 border-dark bg-info rounded p-3">
        <Link to="/" className="h1 text-decoration-none text-dark">Bibliotèque Java town</Link>
        <Routes>
          <Route exact path="/" element={<HomeComponent />} />
          <Route path="attendant/:id" element={<AttendantComponent />} />
          <Route path="client/:id" element={<ClientComponent />} />
          <Route path="notFound" element={<h2>Page existe pas</h2>} />
          <Route path="*" element={<Navigate replace to={"/notFound"} />} />
        </Routes>
      </div >
    </Router>
  );
}

export default App;
