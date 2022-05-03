import React from 'react';
import { useState } from 'react';
import PropTypes from 'prop-types';

const AddClientComponent = () => {
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");

    const postClient = async (client) => {
        await fetch("http://localhost:8080/clients",
            {
                method: "POST",
                headers: {
                    "content-type": "application/json"
                },
                body: JSON.stringify(client)
            });
        setName("");
        setPassword("");
    }

    const onSubmit = (form) => {
        form.preventDefault();
        postClient({
            name,
            password,
        });
    }

    return (
        <form className="col-10 mx-auto" onSubmit={onSubmit}>
            <div className="form-group">
                <label>Nom</label>
                <input className="form-control" required minLength="3" type="text" value={name} onChange={(field) => setName(field.target.value)}></input>
            </div>
            <div className="form-group">
                <label>Mot de passe</label>
                <input className="form-control" required minLength="3" type="password" value={password} onChange={(field) => setPassword(field.target.value)}></input>
            </div>
            <input type="submit" value="Enregistrer" className="btn btn-primary" />
        </form>
    );
}

export default AddClientComponent;

AddClientComponent.propTypes =
{
    postClient: PropTypes.func,
}