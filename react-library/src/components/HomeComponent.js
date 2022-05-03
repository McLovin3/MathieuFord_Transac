import React from 'react';
import { useState, useEffect } from 'react';
import UsersComponent from './UsersComponents';

const HomeComponent = () => {
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
        const response = await fetch("http://localhost:8080/clients");
        const clients = await response.json();
        return clients;
    }

    const fetchEmployees = async () => {
        const response = await fetch("http://localhost:8080/attendants");
        const employees = await response.json();
        return employees;
    }

    return (
        <div>
            <UsersComponent isClient={isClients} users={users}></UsersComponent>
            <button className="btn btn-primary" onClick={() => { setIsClients(!isClients); }}>
                {isClients ? "Je suis un employé" : "Je suis un client"}</button>
        </div>
    );
}

export default HomeComponent;


