import React from "react";
import { useState } from "react";

const AddCDComponent = () => {
    const [title, setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [publicationYear, setPublicationYear] = useState("");
    const [runtime, setRuntime] = useState("");
    const [nbCopies, setNbCopies] = useState("");

    const postCD = async (CD) => {
        await fetch("http://localhost:5000/documents",
            {
                method: "POST",
                headers: {
                    "content-type": "application/json"
                },
                body: JSON.stringify(CD)
            });
    }

    const onSubmit = async (form) => {
        form.preventDefault();
        await postCD({
            title,
            author,
            publicationYear,
            runtime,
            nbCopies,
            documentType: "CD"
        });
        setTitle("");
        setAuthor("");
        setPublicationYear("");
        setRuntime("");
        setNbCopies("");
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
                <label>Année de publication</label>
                <input className="form-control" required min="1" type="number" value={publicationYear} onChange={(field) => setPublicationYear(field.target.value)}></input>
            </div>
            <div className="form-group">
                <label>Durée</label>
                <input className="form-control" required min="1" type="number" value={runtime} onChange={(field) => setRuntime(field.target.value)}></input>
            </div>
            <div className="form-group">
                <label>Nombre d'exemplaires</label>
                <input className="form-control" required min="1" type="number" value={nbCopies} onChange={(field) => setNbCopies(field.target.value)}></input>
            </div>
            <input type="submit" value="Enregistrer" className="btn btn-primary" />
        </form>
    );
}

export default AddCDComponent;