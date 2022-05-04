import React from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';

const UsersComponent = ({ users, isClient }) => {
    return (<div className='row'>
        {users.map((user) => (<Link key={user.id} to={isClient ? "client/" + user.id : "attendant/" + user.id} state={{ userId: user.id }}>{user.name}</Link>))}
    </div>)
}

export default UsersComponent;

UsersComponent.propTypes =
{
    users: PropTypes.array,
    onClick: PropTypes.func
}