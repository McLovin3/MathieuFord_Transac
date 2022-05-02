import React from 'react';
import { useState } from 'react';

const DocumentComponent = ({ document }) => {
    const [documentState, setDocumentState] = useState(document);

    //TODO do I manage dates here or in backend?
    const postBorrow = async () => {
        const path = window.location.href.split("/");
        const clientId = path.at(path.length - 2);
        await fetch("http://localhost:5000/borrows",
            {
                method: "POST",
                headers:
                {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ "documentId": document.id, "clientId": clientId })
            }
        );
        document.nbCopies--;
        setDocumentState(document);
    }

    return (
        <tr>
            <td>{documentState.title}</td>
            <td>{documentState.author}</td>
            <td>{documentState.bookType == null ? "X" : documentState.bookType}</td>
            <td>{documentState.nbPages == null ? "X" : documentState.nbPages}</td>
            <td>{documentState.editor == null ? "X" : documentState.editor}</td>
            <td>{documentState.publicationYear}</td>
            <td>{documentState.runtime == null ? "X" : documentState.runtime}</td>
            <td>{documentState.documentType}</td>
            <td>{documentState.nbCopies}</td>
            <td>{documentState.nbCopies === 0 ? "" : <button className="btn btn-primary" onClick={postBorrow}>Emprunter</button>}</td>
        </tr>
    );
}

export default DocumentComponent;
