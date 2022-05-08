import React from "react";
import { putFines } from "../services/Service";
import PropTypes from 'prop-types';

const FineComponent = ({ clientId, setFines }) => {
    const onClick = async () => {
        await putFines(clientId);
        setFines(0);
    }

    return (
        <button className="btn btn-success" onClick={onClick}>Payer mes amendes</button>
    );
}

export default FineComponent;

FineComponent.propTypes =
{
    clientId: PropTypes.number,
    setFines: PropTypes.func
}