import React from 'react';

const DocumentComponent = ({ document }) => {
    return (
        <tr>
            <td>{document.title}</td>
            <td>{document.author}</td>
            <td>{document.bookType}</td>
            <td>{document.nbPages}</td>
            <td>{document.editor}</td>
            <td>{document.publicationYear}</td>
            <td>{document.nbCopies}</td>
        </tr>
    );
}

export default DocumentComponent;
