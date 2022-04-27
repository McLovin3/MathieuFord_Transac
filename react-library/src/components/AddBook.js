import PropTypes from 'prop-types';
import { useState } from 'react';

export default function AddBook({ postBook }) {
    const [title, setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [type, setType] = useState("");
    const [nbPages, setNbPages] = useState("");
    const [editor, setEditor] = useState("");
    const [publicationYear, setPublicationYear] = useState("");
    const [nbCopies, setNbCopies] = useState("");


    const onSubmit = (form) => {
        form.preventDefault();
        //TODO object?
        postBook(title, author, type, nbPages, editor, publicationYear, nbCopies);
    }

    return (
        <form className='add-form' onSubmit={onSubmit}>
            <div className='form-control'>
                <label for="title">Titre</label>
                <input id="title" required minLength="5" type="text" value={title} onChange={(field) => setTitle(field.target.value)}></input>
            </div>
            <div className='form-control'>
                <label for="autheur">Autheur</label>
                <input id="autheur" required minLength="3" type="text" value={author} onChange={(field) => setAuthor(field.target.value)}></input>
            </div>
            <div className='form-control'>
                <label for="type">Type de livre</label>
                <select id="type" value={type} onChange={(field) => setType(field.target.value)}>
                    <option value="Novel">Roman</option>
                    <option value="School">École</option>
                    <option value="Study">Étude</option>
                    <option value="Magazine">Magazine</option>
                </select>
            </div>
            <div className='form-control'>
                <label for="nbPages">Nombre de pages</label>
                <input id="nbPages" required min="1" type="number" value={nbPages} onChange={(field) => setNbPages(field.target.value)}></input>
            </div>
            <div className='form-control'>
                <label for="editor">Éditeur</label>
                <input id="editor" required minLength="3" type="text" value={editor} onChange={(field) => setEditor(field.target.value)}></input>
            </div>
            <div className='form-control'>
                <label for="publicationYear">Année de publication</label>
                <input id="publicationYear" required min="1" type="number" value={publicationYear} onChange={(field) => setPublicationYear(field.target.value)}></input>
            </div>
            <div className='form-control'>
                <label for="nbCopies">Nombre d'exemplaires</label>
                <input id="nbCopies" required min="1" type="number" value={nbCopies} onChange={(field) => setNbCopies(field.target.value)}></input>
            </div>
            <input type='submit' value='Enregistrer' className='btn btn-block' />
        </form>
    );
}

AddBook.propTypes =
{
    onSubmit: PropTypes.func,
}