import React, { useState } from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";

const ClientComponent = () => {

    const [client, setClient] = useState("");

    useEffect(() => {
        const getClient = async () => {
            const client = await fetchClient();
            setClient(client);
        }
        getClient();
    }, []);

    const fetchClient = async () => {
        const response = await fetch("http://localhost:5000/clients/" + window.location.href.split("/").pop());
        const client = await response.json();
        return client;
    }

    return (
        <div className="row p-5">
            <h2>{client.name}</h2>
            <div className="col-6">
                <Link className="btn btn-primary" to="books">Livres</Link>
            </div>
            <div className="col-6">
                <Link className="btn btn-primary" to="borrows" state={{ "userId": client.id }}>Emprunts</Link>
            </div>
        </div>
    );
}

export default ClientComponent;