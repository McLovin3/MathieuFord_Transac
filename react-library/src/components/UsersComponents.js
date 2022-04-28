import PropTypes from 'prop-types';

export default function UsersComponent({ users, isClient, onClick }) {



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

UsersComponent.propTypes =
{
    users: PropTypes.array,
    onClick: PropTypes.func
}