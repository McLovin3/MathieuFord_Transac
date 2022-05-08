import React from 'react';
import { useEffect, useState } from 'react';
import DocumentComponent from './DocumentComponent';
import { fetchDocuments } from '../services/Service';

const DocumentsComponent = ({ clientId }) => {
    const [documents, setDocuments] = useState([]);

    useEffect(() => {
        fetchDocuments()
            .then(Response => {
                return Response.json();
            })
            .then(data => {
                setDocuments(data);
            });
    }, []);

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
                            {documents.map((document) => <DocumentComponent key={document.id} clientId={clientId} document={document} />)}
                        </>
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default DocumentsComponent;