import User from "../models/User"
import UserComponent from "./UserComponent"

export default function UsersComponent({ users }: { users: User[]} ) {
    return (<>
        {users.map((user) => (<UserComponent key={user.id} user={user}></UserComponent>))}
    </>)
}
