import Book from '../models/Book';

export default function BookComponent({ book }) {
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

BookComponent.propTypes =
{
    book: Book,
}

