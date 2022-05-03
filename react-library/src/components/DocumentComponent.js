import React from 'react';
import { useState } from 'react';
import { postBorrow } from '../services/Service';

const DocumentComponent = ({ document }) => {
    const [documentState, setDocumentState] = useState(document);
    const [errorMessage, setErrorMessage] = useState("");

    const onClick = async () => {
        const path = window.location.href.split("/");
        const clientId = path.at(path.length - 2);
        const response = await postBorrow(document.id, clientId);
        if (!response.ok) {
            setErrorMessage("Le client a déjà ce document");
        }
        else {
            document.nbCopies--;
            setDocumentState(document);
        }
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
            <td>
                {documentState.nbCopies === 0 ? "" : <button className="btn btn-primary" onClick={onClick}>Emprunter</button>}
                <p className="text-danger">{errorMessage}</p>
            </td>
        </tr>
    );
}

export default DocumentComponent;
