import React from 'react';
import { useState } from 'react';
import { postBorrow } from '../services/Service';

const DocumentComponent = ({ document, clientId }) => {
    const [nbCopies, setNbCopies] = useState(document.nbCopies);
    const [errorMessage, setErrorMessage] = useState("");

    const onClick = async () => {
        const response = await postBorrow(document.id, clientId);
        if (!response.ok) {
            setErrorMessage("Le client a déjà ce document");
        }
        else {
            setNbCopies(--document.nbCopies);
        }
    }

    return (
        <tr>
            <td>{document.title}</td>
            <td>{document.author}</td>
            <td>{document.bookType == null ? "X" : document.bookType}</td>
            <td>{document.nbPages === 0 ? "X" : document.nbPages}</td>
            <td>{document.editor == null ? "X" : document.editor}</td>
            <td>{document.publicationYear}</td>
            <td>{document.runtime === 0 ? "X" : document.runtime}</td>
            <td>{document.documentType}</td>
            <td>{nbCopies}</td>
            <td>
                {nbCopies === 0 ? "" : <button className="btn btn-primary" onClick={onClick}>Emprunter</button>}
                <p className="text-danger">{errorMessage}</p>
            </td>
        </tr>
    );
}

export default DocumentComponent;
