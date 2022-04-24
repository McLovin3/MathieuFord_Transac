import User from "../models/User";

export default function UserComponent({ user }: { user: User }) {
    return (<div>
        <h2>{user.name}</h2>
    </div>);
}
