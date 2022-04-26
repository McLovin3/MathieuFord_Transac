import User from "../models/User";

export default function UserComponent({ user }) {
    return (<div>
        <h2>{user.name}</h2>
    </div>);
}

UserComponent.PropTypes = 
{
    user : User,
}
