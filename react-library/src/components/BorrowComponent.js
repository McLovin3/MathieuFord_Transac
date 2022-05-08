import React from "react";
import { useState } from "react";
import { fetchClientFines, putBorrow } from "../services/Service";

const BorrowComponent = ({ borrow, setFines, clientId }) => {
    const [returned, setReturned] = useState(borrow.returned);

    const onClick = async () => {
        borrow.returned = true;
        await putBorrow(borrow);
        setReturned(true);
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
            <td>{borrow.documentName}</td>
            <td>{borrow.borrowDate}</td>
            <td>{borrow.returnDate}</td>
            <td>{returned ? "Retourner" : <button className="btn btn-success" onClick={onClick}>Retourner</button>}</td>
        </tr>
    );
}

export default BorrowComponent;