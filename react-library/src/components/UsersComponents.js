import PropTypes from 'prop-types';

export default function UsersComponent({ users, onClick }) {



    return (<>
        {users.map((user) => (<h3 key={user.id} onClick={() => onClick(user)} user={user}>{user.name}</h3>))}
    </>)
}

UsersComponent.propTypes =
{
    users: PropTypes.array,
    onClick: PropTypes.func
}