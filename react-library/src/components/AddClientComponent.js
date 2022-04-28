import PropTypes from 'prop-types';
import { useState } from 'react';

export default function AddClientComponent({ postClient }) {
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");

    const onSubmit = (form) => {
        form.preventDefault();
        postClient({
            name,
            password,
        });
        //TODO redirect to Client page
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

AddClientComponent.propTypes =
{
    postClient: PropTypes.func,
}