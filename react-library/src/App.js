import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import AddBookComponent from './components/AddBookComponent';
import BooksComponent from './components/BooksComponent';

function App() {

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

  return (
    <Router>
      <div className="col-6 text-center mx-auto mt-5 border border-2 border-dark bg-info rounded">
        <h1>Bibliotèque Java town</h1>
        <Routes>
          <Route path="addBook" element={<AddBookComponent postBook={postBook} />} />
          <Route path="books" element={<BooksComponent />} />
        </Routes>
      </div >
    </Router>
  );
}


export default App;
