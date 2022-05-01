import React from "react";
import { Link } from "react-router-dom";

const ClientComponent = () => {
    return (
        <div className="row p-5">
            <div className="col-6">
                <Link className="btn btn-primary" to="books">Livres</Link>
            </div>
        </div>
    );
}

export default ClientComponent;