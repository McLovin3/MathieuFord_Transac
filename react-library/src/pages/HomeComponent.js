import React from 'react';
import { useState, useEffect } from 'react';
import UsersComponent from '../components/UsersComponents';
import { fetchAttendants, fetchClients } from '../services/Service';

const HomeComponent = () => {
    const [users, setUsers] = useState([]);
    const [isClients, setIsClients] = useState(true);

    useEffect(() => {
        const getUsers = async () => {
            const users = isClients ? await (await fetchClients()).json() : await (await fetchAttendants()).json();
            setUsers(users);
        }
        getUsers();
    }, [isClients]);

    return (
        <div>
            <UsersComponent isClient={isClients} users={users}></UsersComponent>
            <button className="btn btn-primary" onClick={() => { setIsClients(!isClients); }}>
                {isClients ? "Je suis un employé" : "Je suis un client"}</button>
        </div>
    );
}

export default HomeComponent;


