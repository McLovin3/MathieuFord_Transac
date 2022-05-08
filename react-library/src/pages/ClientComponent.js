import React, { useState } from "react";
import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { fetchClient, fetchClientFines } from "../services/Service";
import BorrowsComponent from "../components/BorrowsComponent";
import DocumentsComponent from "../components/DocumentsComponent";
import FineComponent from "../components/FineComponent";

const ClientComponent = () => {
    const location = useLocation();
    const [client, setClient] = useState("");
    const [component, setComponent] = useState(<></>);
    const [fines, setFines] = useState(0);

    useEffect(() => {
        const getClient = async () => {
            try {
                const response = await fetchClient(location.state.userId);
                if (response.ok) setClient(await response.json());
                else window.location.href = "/";
            }
            catch (error) {
                window.location.href = "/";
            }
            setFines(await fetchClientFines(client.id).json);
        }
        getClient();
    }, [location, client]);

    return client !== "" ? (
        <div className="col">
            <div className="row p-5">
                <h2>{client.name}</h2>
                <div className="col-6">
                    <button className="btn btn-primary" onClick={() => setComponent(fines === 0 ? <DocumentsComponent clientId={client.id} /> : <h2 className="text-danger">Client a des amendes</h2>)}>Documents</button>
                </div>
                <div className="col-6">
                    <button className="btn btn-primary" onClick={() => setComponent(<BorrowsComponent clientId={client.id} />)} > Emprunts</button>
                </div>
                <div className="col-6">
                    <button className="btn btn-primary" onClick={() => setComponent(fines === 0 ? <h2 className="text-success">Aucune amende</h2> : <FineComponent clientId={client.id} setFines={setFines} />)} > Amendes</button>
                </div>
            </div>
            {component}
        </div >
    ) : <></>;
}

export default ClientComponent;