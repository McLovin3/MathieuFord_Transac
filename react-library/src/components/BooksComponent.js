import React from 'react';
import { useEffect, useState } from 'react';
import BookComponent from './BookComponent';

const BooksComponent = () => {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        const getBooks = async () => {
            const books = await fetchBooks();
            setBooks(books);
        }
        getBooks();
    }, []);


    const fetchBooks = async () => {
        const response = await fetch("http://localhost:5000/books");
        const books = await response.json();
        return books;
    }

    return (
        <div>
            <h2>Livres</h2>
            <div className="col-10 mx-auto">
                <table className="table">
                    <thead>
                        <tr>
                            <th>Titre</th>
                            <th>Autheur</th>
                            <th>Type de livre</th>
                            <th>Nombre de pages</th>
                            <th>Éditeur</th>
                            <th>Année de publication</th>
                            <th>Exemplaires disponibles</th>
                        </tr>
                    </thead>
                    <tbody>
                        <>
                            {books.map((book) => <BookComponent key={book.id} book={book} />)}
                        </>
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default BooksComponent;