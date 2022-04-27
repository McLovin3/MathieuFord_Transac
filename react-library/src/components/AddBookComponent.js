import PropTypes from 'prop-types';
import { useState } from 'react';

export default function AddBookComponent({ postBook }) {
    const [title, setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [bookType, setBookType] = useState("Novel");
    const [nbPages, setNbPages] = useState("");
    const [editor, setEditor] = useState("");
    const [publicationYear, setPublicationYear] = useState("");
    const [nbCopies, setNbCopies] = useState("");


    const onSubmit = async (form) => {
        form.preventDefault();
        await postBook({
            title,
            author,
            bookType,
            nbPages,
            editor,
            publicationYear,
            nbCopies
        });
        window.location.href = '/books'
    }

    return (
        <form className="col-10 mx-auto" onSubmit={onSubmit}>
            <div className="form-group">
                <label>Titre</label>
                <input className="form-control" required minLength="3" type="text" value={title} onChange={(field) => setTitle(field.target.value)}></input>
            </div>
            <div className="form-group">
                <label>Autheur</label>
                <input className="form-control" required minLength="3" type="text" value={author} onChange={(field) => setAuthor(field.target.value)}></input>
            </div>
            <div className="form-group">
                <label>Type de livre</label>
                <select className="form-control" value={bookType} onChange={(field) => setBookType(field.target.value)}>
                    <option value="Novel">Roman</option>
                    <option value="School">École</option>
                    <option value="Study">Étude</option>
                    <option value="Magazine">Magazine</option>
                </select>
            </div>
            <div className="form-group">
                <label>Nombre de pages</label>
                <input className="form-control" required min="1" type="number" value={nbPages} onChange={(field) => setNbPages(field.target.value)}></input>
            </div>
            <div className="form-group">
                <label>Éditeur</label>
                <input className="form-control" required minLength="3" type="text" value={editor} onChange={(field) => setEditor(field.target.value)}></input>
            </div>
            <div className="form-group">
                <label>Année de publication</label>
                <input className="form-control" required min="1" type="number" value={publicationYear} onChange={(field) => setPublicationYear(field.target.value)}></input>
            </div>
            <div className="form-group">
                <label>Nombre d'exemplaires</label>
                <input className="form-control" required min="1" type="number" value={nbCopies} onChange={(field) => setNbCopies(field.target.value)}></input>
            </div>
            <input type="submit" value="Enregistrer" className="btn" />
        </form>
    );
}

AddBookComponent.propTypes =
{
    postBook: PropTypes.func,
}