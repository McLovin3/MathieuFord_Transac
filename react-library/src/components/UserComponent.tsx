import User from "../models/User";

const UserComponent = ({ user }: { user: User }) => {
    return (<div>
        <h2>{user.name}</h2>
    </div>)
}

export default UserComponent;