import React from "react";
import { Link } from "react-router-dom";

const EmployeeComponent = () => {
    return (
        <Link className="btn btn-primary" to="/addClient">Ajouter un client</Link>
    );
}

export default EmployeeComponent;

