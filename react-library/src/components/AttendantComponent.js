import React from "react";
import { useState, useEffect } from "react";
import { fetchAttendant } from "../services/Service";
import AddBookComponent from "./AddBookComponent";
import AddCDComponent from "./AddCDComponent";
import AddClientComponent from "./AddClientComponent";
import AddDVDComponent from "./AddDVDComponent";

const AttendantComponent = () => {

    const [attendant, setAttendant] = useState("");
    const [component, setComponent] = useState(<></>);

    useEffect(() => {
        const getAttendant = async () => {
            try {
                const response = await fetchAttendant(window.location.href.split("/").pop());
                if (response.ok) setAttendant(response.json());
                else window.location.href = "/";
            }
            catch (error) {
                window.location.href = "/";
            }
        }
        getAttendant();
    }, []);

    return (
        <div className="col">
            <div className="row p-5">
                <h2>{attendant.name}</h2>
                <div className="col-3">
                    <button className="btn btn-primary" onClick={() => setComponent(<AddClientComponent />)}>Ajouter un client</button>
                </div>
                <div className="col-3">
                    <button className="btn btn-primary" onClick={() => setComponent(<AddBookComponent />)}>Ajouter un Livre</button>
                </div>
                <div className="col-3">
                    <button className="btn btn-primary" onClick={() => setComponent(<AddCDComponent />)}>Ajouter un CD</button>
                </div>
                <div className="col-3">
                    <button className="btn btn-primary" onClick={() => setComponent(<AddDVDComponent />)}>Ajouter un DVD</button>
                </div>
            </div>
            {component}
        </div>
    );
}

export default AttendantComponent;

