import React from "react";

const BorrowComponent = ({ borrow, returnDocument }) => {

    const temp = () => {
        returnDocument(borrow)
    }

    return (
        <tr>
            <td>{borrow.documentName}</td>
            <td>{borrow.borrowDate}</td>
            <td>{borrow.returnDate}</td>
            <td>{borrow.returned ? "Retourner" : <button className="btn btn-success" onClick={temp}>Retourner</button>}</td>
        </tr>
    );
}

export default BorrowComponent;