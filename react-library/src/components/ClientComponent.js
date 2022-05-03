import React, { useState } from "react";
import { useEffect } from "react";
import { fetchClient } from "../services/Service";
import BorrowsComponent from "./BorrowsComponent";
import DocumentsComponent from "./DocumentsComponent";

const ClientComponent = () => {
    const [client, setClient] = useState("");
    const [component, setComponent] = useState(<></>);

    useEffect(() => {
        const getClient = async () => {
            const client = await (await fetchClient(window.location.href.split("/").pop())).json();
            setClient(client);
        }
        getClient();
    }, []);

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