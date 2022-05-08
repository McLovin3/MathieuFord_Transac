import React from "react";
import { useState, useEffect } from "react";
import PropTypes from 'prop-types';
import BorrowComponent from "./BorrowComponent";
import { fetchBorrows } from "../services/Service";

const BorrowsComponent = ({ clientId, setFines }) => {
    const [borrows, setBorrows] = useState([]);

    useEffect(() => {
        fetchBorrows(clientId)
            .then(response => {
                return response.json();
            })
            .then(data => {
                setBorrows(data)
            });
    }, [clientId]);

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
                            {borrows.map((borrow) => <BorrowComponent key={borrow.id} clientId={clientId} borrow={borrow} setFines={setFines}/>)}
                        </>
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default BorrowsComponent;

BorrowsComponent.propTypes = {
    clientId: PropTypes.number,
}