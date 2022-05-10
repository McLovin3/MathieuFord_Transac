import React from "react";
import { useState } from "react";
import { putClient } from "../services/Service";

const EditClientComponent = ({ client }) => {
    const [name, setName] = useState(client.name);
    const [password, setPassword] = useState(client.password);

    const onSubmit = async (form) => {
        form.preventDefault();
        await putClient({
            "id": client.id,
            "name": name,
            "password": password,
        });
        window.location.href = "/";
    }

    return (
        <form className="col-10 mx-auto" onSubmit={onSubmit}>
            <div className="form-group">
                <label>Nom</label>
                <input className="form-control" required minLength="3" type="text" value={name} onChange={(field) => setName(field.target.value)}></input>
            </div>
            <div className="form-group">
                <label>Mot de passe</label>
                <input className="form-control" required minLength="3" type="text" value={password} onChange={(field) => setPassword(field.target.value)}></input>
            </div>
            <input type="submit" value="Enregistrer" className="btn btn-primary" />
        </form>
    );
}

export default EditClientComponent;