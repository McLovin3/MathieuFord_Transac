import React from 'react';
import PropTypes from 'prop-types';

const UsersComponent = ({ users, isClient, onClick }) => {
    return (<>
        {users.map((user) => (<h3 key={user.id} onClick={() => {
            onClick(user);
            if (isClient) {
                window.location.href = "/client";
            }
            else window.location.href = "/employee";
        }} user={user}>{user.name}</h3>))}
    </>)
}

export default UsersComponent;

UsersComponent.propTypes =
{
    users: PropTypes.array,
    onClick: PropTypes.func
}