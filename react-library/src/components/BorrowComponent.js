import React from "react";
import { useState } from "react";
import { fetchClientFines, putBorrow } from "../services/Service";

const BorrowComponent = ({ borrow, setFines, clientId }) => {
    const [borrowState, setBorrowState] = useState(borrow);

    const onClick = async () => {
        borrow.returned = true;
        await putBorrow(borrow);
        setBorrowState(borrow);
        fetchClientFines(clientId)
            .then(response => {
                return response.json();
            })
            .then(data => {
                setFines(data);
            })
    }

    return (
        <tr>
            <td>{borrowState.documentName}</td>
            <td>{borrowState.borrowDate}</td>
            <td>{borrowState.returnDate}</td>
            <td>{borrowState.returned ? "Retourner" : <button className="btn btn-success" onClick={onClick}>Retourner</button>}</td>
        </tr>
    );
}

export default BorrowComponent;