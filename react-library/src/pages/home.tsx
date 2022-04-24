import { useState } from "react";
import ButtonComponent from "../components/ButtonComponent";
import UsersComponent from "../components/UsersComponent";

export default function Home() {
    const clients = [
        {
            id: 1,
            name: 'Homer',
        },
        {
            id: 2,
            name: 'Bart',
        },
        {
            id: 3,
            name: 'Maggie',
        },
    ];

    const employees = [
        {
            id: 1,
            name: 'Fry',
        },
        {
            id: 2,
            name: 'Leela',
        },
        {
            id: 3,
            name: 'Bender',
        },
    ];

    const [users, setUsers] = useState(clients);
    const [isClients, setIsClients] = useState(true);

    return (<div style={{ textAlign: "center" }}>
        <h1>Bibliothèque Java Town</h1>
        <h2>{isClients ? "Clients : " : "Employés : "}</h2>
        <UsersComponent users={users}></UsersComponent>
        <ButtonComponent text={isClients ? "Je suis un employé" : "Je suis un client"} onClick={() => {
            setIsClients(!isClients);
            setUsers(!isClients ? clients : employees);
        }}></ButtonComponent>
    </div>);
}