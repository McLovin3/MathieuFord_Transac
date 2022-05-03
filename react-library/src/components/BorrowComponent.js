import React from "react";
import { useState } from "react";

const BorrowComponent = ({ borrow }) => {
    const [borrowState, setBorrowState] = useState(borrow);

    const putBorrow = async () => {
        borrow.returned = true;
        await fetch("http://localhost:8080/borrows/" + borrow.id,
            {
                method: "PUT",
                headers:
                {
                    "Content-Type":
                        "application/json"
                },
                body:
                    JSON.stringify(borrow)
            });
        setBorrowState(borrow);
    }


    return (
        <tr>
            <td>{borrowState.documentName}</td>
            <td>{borrowState.borrowDate}</td>
            <td>{borrowState.returnDate}</td>
            <td>{borrowState.returned ? "Retourner" : <button className="btn btn-success" onClick={putBorrow}>Retourner</button>}</td>
        </tr>
    );
}

export default BorrowComponent;