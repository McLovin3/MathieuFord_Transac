import PropTypes from 'prop-types';
import { useState } from 'react';

export default function AddBook({ postBook }) {
    const [title, setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [bookType, setBookType] = useState("");
    const [nbPages, setNbPages] = useState("");
    const [editor, setEditor] = useState("");
    const [publicationYear, setPublicationYear] = useState("");
    const [nbCopies, setNbCopies] = useState("");


    const onSubmit = (form) => {
        form.preventDefault();
        postBook({
            title,
            author,
            bookType,
            nbPages,
            editor,
            publicationYear,
            nbCopies
        });
        //TODO redirect to book page
    }

    return (
        <form className='add-form' onSubmit={onSubmit}>
            <div className='form-control'>
                <label>Titre</label>
                <input required minLength="5" type="text" value={title} onChange={(field) => setTitle(field.target.value)}></input>
            </div>
            <div className='form-control'>
                <label>Autheur</label>
                <input required minLength="3" type="text" value={author} onChange={(field) => setAuthor(field.target.value)}></input>
            </div>
            <div className='form-control'>
                <label>Type de livre</label>
                <select value={bookType} onChange={(field) => setBookType(field.target.value)}>
                    <option value="Novel">Roman</option>
                    <option value="School">École</option>
                    <option value="Study">Étude</option>
                    <option value="Magazine">Magazine</option>
                </select>
            </div>
            <div className='form-control'>
                <label>Nombre de pages</label>
                <input required min="1" type="number" value={nbPages} onChange={(field) => setNbPages(field.target.value)}></input>
            </div>
            <div className='form-control'>
                <label>Éditeur</label>
                <input required minLength="3" type="text" value={editor} onChange={(field) => setEditor(field.target.value)}></input>
            </div>
            <div className='form-control'>
                <label>Année de publication</label>
                <input required min="1" type="number" value={publicationYear} onChange={(field) => setPublicationYear(field.target.value)}></input>
            </div>
            <div className='form-control'>
                <label>Nombre d'exemplaires</label>
                <input required min="1" type="number" value={nbCopies} onChange={(field) => setNbCopies(field.target.value)}></input>
            </div>
            <input type='submit' value='Enregistrer' className='btn btn-block' />
        </form>
    );
}

AddBook.propTypes =
{
    postBook: PropTypes.func,
}