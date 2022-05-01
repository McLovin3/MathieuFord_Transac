import React from "react";
import { Link } from "react-router-dom";

const AttendantComponent = () => {
    return (
        <div className="row p-5">
            <div className="col-6">
                <Link className="btn btn-primary" to="/addClient">Ajouter un client</Link>
            </div>
            <div className="col-6">
                <Link className="btn btn-primary" to="/addBook">Ajouter un Livre</Link>
            </div>
        </div>
    );
}

export default AttendantComponent;

