import PropTypes from 'prop-types';

export default function UserComponent({ user, onClick }) {
    return (<div>
        <link onClick={onClick}>{user.name}</link>
    </div>);
}

UserComponent.propTypes =
{
    onClick: PropTypes.func,
}