import React from 'react';
import { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import UsersComponent from './UsersComponents';

const HomeComponent = ({ onClick }) => {
    const [users, setUsers] = useState([]);
    const [isClients, setIsClients] = useState(true);

    useEffect(() => {
        const getUsers = async () => {
            const users = isClients ? await fetchClients() : await fetchEmployees();
            setUsers(users);
        }
        getUsers();
    }, [isClients]);

    const fetchClients = async () => {
        const response = await fetch("http://localhost:5000/clients");
        const clients = await response.json();
        return clients;
    }

    //TODO move this to app
    const fetchEmployees = async () => {
        const response = await fetch("http://localhost:5000/attendants");
        const employees = await response.json();
        return employees;
    }

    return (
        <div>
            <UsersComponent users={users} onClick={onClick}></UsersComponent>
            <button className="btn btn-primary" onClick={() => { setIsClients(!isClients); }}>
                {isClients ? "Je suis un employé" : "Je suis un client"}</button>
        </div>
    );
}

export default HomeComponent;

HomeComponent.propTypes =
{
    onClick: PropTypes.func,
}
