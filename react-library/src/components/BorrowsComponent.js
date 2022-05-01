import React from "react";
import { useState, useEffect } from "react";
import PropTypes from 'prop-types';
import BorrowComponent from "./BorrowComponent";
import { useLocation } from "react-router-dom";

const BorrowsComponent = () => {

    const userId = useLocation().state.userId;
    const [borrows, setBorrows] = useState([]);

    useEffect(() => {
        const getBorrows = async () => {
            let tempBorrows = await fetchBorrows();
            tempBorrows = Array.from(tempBorrows).filter(borrow => borrow.clientId === userId);
            setBorrows(tempBorrows);
        }
        getBorrows();
    }, [userId]);

    const fetchBorrows = async () => {
        const response = await fetch("http://localhost:5000/borrows");
        const tempBorrows = response.json();
        return tempBorrows;
    }

    const putBorrow = async (borrow) => {
        const borrowIndex = borrows.findIndex(tempBorrow => tempBorrow.id === borrow.id);
        borrows.at(borrowIndex).returned = true;
        setBorrows([...borrows]);
        await fetch("http://localhost:5000/borrows/" + borrow.id,
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
    }

    return (
        <div>
            <h2>Emprunts</h2>
            <div className="col-10 mx-auto">
                <table className="table">
                    <thead>
                        <tr>
                            <th>Livre</th>
                            <th>Date d'emprunt</th>
                            <th>Date de retour</th>
                            <th>Retourner</th>
                        </tr>
                    </thead>
                    <tbody>
                        <>
                            {borrows.map((borrow) => <BorrowComponent key={borrow.id} borrow={borrow} returnDocument={putBorrow} />)}
                        </>
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default BorrowsComponent;

BorrowsComponent.propTypes = {
    user: PropTypes.string,
}