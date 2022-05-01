import React from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';

const UsersComponent = ({ users, isClient, onClick }) => {
    return (<>
        {users.map((user) => (<Link key={user.id} to={isClient ? "client" : "attendant"} onClick={onClick(user)}>{user.name}</Link>))}
    </>)
}

export default UsersComponent;

UsersComponent.propTypes =
{
    users: PropTypes.array,
    onClick: PropTypes.func
}