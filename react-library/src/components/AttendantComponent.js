import React from "react";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { fetchAttendant } from "../services/Service";

const AttendantComponent = () => {

    const [attendant, setAttendant] = useState("");

    useEffect(() => {
        const getAttendant = async () => {
            const attendant = await fetchAttendant(window.location.href.split("/").pop());
            setAttendant(attendant);
        }
        getAttendant();
    }, []);

    return (
        <div className="row p-5">
            <h2>{attendant.name}</h2>
            <div className="col-3">
                <Link className="btn btn-primary" to="addClient">Ajouter un client</Link>
            </div>
            <div className="col-3">
                <Link className="btn btn-primary" to="addBook">Ajouter un Livre</Link>
            </div>
            <div className="col-3">
                <Link className="btn btn-primary" to="addCD">Ajouter un CD</Link>
            </div>
            <div className="col-3">
                <Link className="btn btn-primary" to="addDVD">Ajouter un DVD</Link>
            </div>
        </div>
    );
}

export default AttendantComponent;

