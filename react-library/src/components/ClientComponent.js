import React, { useState } from "react";
import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { fetchClient } from "../services/Service";
import BorrowsComponent from "./BorrowsComponent";
import DocumentsComponent from "./DocumentsComponent";

const ClientComponent = () => {
    const location = useLocation();
    const [client, setClient] = useState("");
    const [component, setComponent] = useState(<></>);

    useEffect(() => {
        const getClient = async () => {
            try {
                const response = await fetchClient(location.state.userId);
                if (response.ok) setClient(await response.json());
                else window.location.href = "/";
            }
            catch (error) {
                window.location.url = "/";
            }
        }
        getClient();
    }, [location]);

    return client !== "" ? (
        <div className="col">
            <div className="row p-5">
                <h2>{client.name}</h2>
                <div className="col-6">
                    <button className="btn btn-primary" onClick={() => setComponent(<DocumentsComponent clientId={client.id} />)}>Documents</button>
                </div>
                <div className="col-6">
                    <button className="btn btn-primary" onClick={() => setComponent(<BorrowsComponent clientId={client.id} />)} > Emprunts</button>
                </div>
            </div>
            {component}
        </div >
    ) : <></>;
}

export default ClientComponent;