import React from "react";
import {Button} from "antd";
import "../css/userExcel.css"
import {postRequest_noRet} from "../utils/ajax";
import {banUser, getUsers} from "../services/userService";

export class UserExcel extends React.Component {
    constructor(props) {
        super(props);

        this.state={
            users: []
        }
    }

    componentDidMount() {
        const callback = (data) => {
            this.setState({
                users: data
            })
        }
        getUsers(callback);
    }

    getRole(role) {
        if (role == -1)
            return "已禁用";
        else if (role == 0)
            return "用户";
        else
            return "管理员";
    }

    setUserRole = (idx, role) => {
        let userId = this.state.users[idx].userId;
        let data = {"userId": userId, "role": role};
        const callback = (data) => {
            this.setState({
                users: data
            })
        }
        banUser(data, callback)
    };

    render() {
        let users = this.state.users;
        return (
            <div className="user-excel">
                {users.map((user, idx) => (
                    <div className="user-excel-item" key={idx}>
                        <div className="user-excel-item-name">
                            <h3>UserName:{user.username}</h3>
                        </div>
                        <div className="user-excel-item-btn">
                            <Button type="primary" onClick={()=>this.setUserRole(idx, -1)}
                                    value={idx} danger>Ban</Button>
                            <Button type="primary" onClick={()=>this.setUserRole(idx, 0)}
                                    value={idx} danger>Reset</Button>
                        </div>
                        <div className="user-excel-item-role">
                            <h3>Role:{this.getRole(user.role)}</h3>
                        </div>
                    </div>
                ))}
            </div>
        );
    }

}
