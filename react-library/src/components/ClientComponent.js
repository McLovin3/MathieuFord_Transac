import React, { useState } from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import { fetchClient } from "../services/Service";

const ClientComponent = () => {

    const [client, setClient] = useState("");

    useEffect(() => {
        const getClient = async () => {
            const client = await fetchClient(window.location.href.split("/").pop());
            setClient(client);
        }
        getClient();
    }, []);

    return (
        <div className="row p-5">
            <h2>{client.name}</h2>
            <div className="col-6">
                <Link className="btn btn-primary" to="documents">Documents</Link>
            </div>
            <div className="col-6">
                <Link className="btn btn-primary" to="borrows" state={{ "userId": client.id }}>Emprunts</Link>
            </div>
        </div>
    );
}

export default ClientComponent;