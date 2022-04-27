import { useEffect, useState } from 'react';
import BookComponent from './BookComponent';

export default function BooksComponent() {
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
        <>
            {books.map((book) => <BookComponent key={book.id} book={book} />)}
        </>
    );
}