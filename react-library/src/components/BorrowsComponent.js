import React from "react";
import { useState, useEffect } from "react";
import PropTypes from 'prop-types';
import BorrowComponent from "./BorrowComponent";
import { useLocation } from "react-router-dom";
import { fetchBorrows } from "../services/Service";

const BorrowsComponent = () => {

    const userId = useLocation().state.userId;
    const [borrows, setBorrows] = useState([]);

    useEffect(() => {
        const getBorrows = async () => {
            const borrows = await fetchBorrows(userId);
            setBorrows(borrows);
        }
        getBorrows();
    }, [userId]);

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
                            {borrows.map((borrow) => <BorrowComponent key={borrow.id} borrow={borrow} />)}
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