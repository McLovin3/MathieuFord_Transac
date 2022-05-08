import React, { useState } from "react";
import { putFines } from "../services/Service";
import PropTypes from 'prop-types';

const FineComponent = ({ clientId, setFines, fines }) => {
    const [paid, setPaid] = useState(fines === 0)

    const onClick = async () => {
        await putFines(clientId);
        setFines(0);
        setPaid(true);
    }

    return (
        paid ? <h2 className="text-success">Aucune amende</h2> :
            <button className="btn btn-success" onClick={onClick}>Payer mes amendes</button>
    );
}

export default FineComponent;

FineComponent.propTypes =
{
    clientId: PropTypes.number,
    setFines: PropTypes.func,
    fines: PropTypes.number
}