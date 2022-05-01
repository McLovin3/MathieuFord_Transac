import React from "react";

const BorrowComponent = ({ borrow }) => {
    return (
        <tr>
            <td>{borrow.documentId}</td>
            <td>{borrow.borrowDate}</td>
            <td>{borrow.returnDate}</td>
            <td>{borrow.returned.toString()}</td>
        </tr>
    );
}

export default BorrowComponent;