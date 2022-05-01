import React from "react";

const BorrowComponent = ({ borrow }) => {
    return (
        <tr>
            <td>{borrow.documentId}</td>
            <td>{borrow.borrowDate}</td>
            <td>{borrow.returnDate}</td>
            <td>{borrow.returned ? "Retourner" : <button className="btn btn-success">Retourner</button>}</td>
        </tr>
    );
}

export default BorrowComponent;