import React from "react";
import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { fetchAttendant } from "../services/Service";
import AddBookComponent from "../components/AddBookComponent";
import AddCDComponent from "../components/AddCDComponent";
import AddClientComponent from "../components/AddClientComponent";
import AddDVDComponent from "../components/AddDVDComponent";

const AttendantComponent = () => {
    const location = useLocation();
    const [attendant, setAttendant] = useState("");
    const [component, setComponent] = useState(<></>);

    useEffect(() => {
        try {
            fetchAttendant(location.state.userId)
                .then(response => {
                    if (response.ok) return response.json();
                    else window.location.href = "/";
                })
                .then(data => {
                    setAttendant(data)
                });
        }
        catch (error) {
            window.location.href = "/";
        }
    }, [location]);

    return attendant !== "" ? (
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
    ) : <></>;
}

export default AttendantComponent;

