import React from 'react';
import Book from '../models/Book';

const BookComponent = ({ book }) => {
    return (
        <tr>
            <td>{book.title}</td>
            <td>{book.author}</td>
            <td>{book.bookType}</td>
            <td>{book.nbPages}</td>
            <td>{book.editor}</td>
            <td>{book.publicationYear}</td>
            <td>{book.nbCopies}</td>
        </tr>
    );
}

export default BookComponent;

BookComponent.propTypes =
{
    book: Book,
}

