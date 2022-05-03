import React from 'react';
import { useEffect, useState } from 'react';
import DocumentComponent from './DocumentComponent';

const DocumentsComponent = () => {
    const [documents, setDocuments] = useState([]);

    useEffect(() => {
        const getDocuments = async () => {
            const documents = await fetchDocuments();
            setDocuments(documents);
        }
        getDocuments();
    }, []);


    const fetchDocuments = async () => {
        const response = await fetch("http://localhost:8080/documents");
        return await response.json();
    }

    return (
        <div>
            <h2>Documents</h2>
            <div className="col-10 mx-auto">
                <table className="table">
                    <thead>
                        <tr>
                            <th>Titre</th>
                            <th>Autheur</th>
                            <th>Type de livre</th>
                            <th>Nombre de pages</th>
                            <th>Éditeur</th>
                            <th>Année de publication</th>
                            <th>Durée</th>
                            <th>Type de document</th>
                            <th>Exemplaires disponibles</th>
                            <th>Emprunter</th>
                        </tr>
                    </thead>
                    <tbody>
                        <>
                            {documents.map((document) => <DocumentComponent key={document.id} document={document} />)}
                        </>
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default DocumentsComponent;