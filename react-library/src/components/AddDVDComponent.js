import React from "react";
import { useState } from "react";

const AddDVDComponent = () => {
    const [title, setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [publicationYear, setPublicationYear] = useState("");
    const [runtime, setRuntime] = useState("");
    const [nbCopies, setNbCopies] = useState("");

    const postDVD = async (DVD) => {
        await fetch("http://localhost:8080/documents",
            {
                method: "POST",
                headers: {
                    "content-type": "application/json"
                },
                body: JSON.stringify(DVD)
            });
    }

    const onSubmit = async (form) => {
        form.preventDefault();
        await postDVD({
            title,
            author,
            publicationYear,
            runtime,
            nbCopies,
            documentType: "DVD"
        });
        setTitle("");
        setAuthor("");
        setPublicationYear("");
        setRuntime("");
        setNbCopies("");
    }

    return (
        <form className="col-10 mx-auto" onSubmit={onSubmit}>
            <h2>Ajout de DVD</h2>
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

export default AddDVDComponent;