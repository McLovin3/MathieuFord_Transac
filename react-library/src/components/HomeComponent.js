import PropTypes from 'prop-types';
import { useState, useEffect } from 'react';
import UsersComponent from './UsersComponents';

export default function HomeComponent({onClick}) {
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
        const response = await fetch("http://localhost:5000/employees");
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


HomeComponent.propTypes =
{
    onClick: PropTypes.func,
}
