import PropTypes from 'prop-types';
import UserComponent from "./UserComponent"

export default function UsersComponent({ users }) {
    return (<>
        {users.map((user) => (<UserComponent key={user.id} user={user}></UserComponent>))}
    </>)
}

UsersComponent.propTypes =
{
    users: PropTypes.array,
}