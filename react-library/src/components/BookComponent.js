import Book from '../models/Book';

export default function BookComponent({ book }) {
    return (
        <div>
            <h2>Livres</h2>
            <div class="col-8">
                <table>
                    <tr>
                        <th>Titre</th>
                        <th>Autheur</th>
                        <th>Type de livre</th>
                        <th>Nombre de pages</th>
                        <th>Éditeur</th>
                        <th>Année de publication</th>
                        <th>Exemplaires disponibles</th>
                    </tr>
                    <tr>
                        <td>{book.title}</td>
                        <td>{book.author}</td>
                        <td>{book.bookType}</td>
                        <td>{book.nbPages}</td>
                        <td>{book.editor}</td>
                        <td>{book.publicationYear}</td>
                        <td>{book.nbCopies}</td>
                    </tr>
                </table>
            </div>
        </div>
    );
}

BookComponent.propTypes =
{
    book: Book,
}

