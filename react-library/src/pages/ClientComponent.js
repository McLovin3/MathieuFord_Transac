import React, { useState } from "react";
import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { fetchClient, fetchClientFines } from "../services/Service";
import BorrowsComponent from "../components/BorrowsComponent";
import DocumentsComponent from "../components/DocumentsComponent";
import FineComponent from "../components/FineComponent";
import EditClientComponent from "../components/EditClientComponent";

const ClientComponent = () => {
    const location = useLocation();
    const [client, setClient] = useState("");
    const [component, setComponent] = useState(<></>);
    const [fines, setFines] = useState(0);

    useEffect(() => {
        try {
            fetchClient(location.state.userId)
                .then(response => {
                    if (response.ok) return response.json();
                    else window.location.href = "/";
                })
                .then(data => {
                    setClient(data)
                });

            fetchClientFines(location.state.userId)
                .then(response => {
                    return response.json();
                })
                .then(data => {
                    setFines(data);
                });
        }
        catch (error) {
            window.location.href = "/";
        }
    }, [location]);

    return client !== "" ? (
        <div className="col">
            <div className="row p-5">
                <h2>{client.name}</h2>
                <div className="col-3">
                    <button className="btn btn-primary" onClick={() => setComponent(fines === 0 ? <DocumentsComponent clientId={client.id} /> : <h2 className="text-danger">Client a des amendes</h2>)}>Documents</button>
                </div>
                <div className="col-3">
                    <button className="btn btn-primary" onClick={() => setComponent(<BorrowsComponent clientId={client.id} setFines={setFines} />)} > Emprunts</button>
                </div>
                <div className="col-3">
                    <button className="btn btn-primary" onClick={() => setComponent(<FineComponent clientId={client.id} setFines={setFines} fines={fines} />)} > Amendes</button>
                </div>
                <div className="col-3">
                    <button className="btn btn-primary" onClick={() => setComponent(<EditClientComponent client={client} setClient={setClient} />)} > Profil</button>
                </div>
            </div>
            {component}
        </div >
    ) : <></>;
}

export default ClientComponent;