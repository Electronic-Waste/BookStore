import React from "react";

import "../css/home.css"
import bookIcon from "../assets/books.png"
import cartIcon from "../assets/mycart.png"
import  orderIcon from "../assets/myorders.png"
import profileIcon from "../assets/myprofile.png"
import {Link} from "react-router-dom";
import {postRequest} from "../utils/ajax";

export class SideBar extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            userId: window.location.href.split('/')[3],
            userRole: 0
        }
    }

    componentDidMount() {
        let data = {"userId": this.state.userId};
        let url = "http://localhost:8080/getUser";
        const callback = (data) => {
            let role = data.data.role;
            this.setState({
                userRole: role
            })
        }
        postRequest(url, data, callback);
    }

    handleClickAddBook = (e) => {
        let openUrl = "http://localhost:3000/" + this.state.userId + "/AddBookView";
        window.open(openUrl, "_self");
    }

    handleClickManageUsers = (e) => {
        let openUrl = "http://localhost:3000/" + this.state.userId + "/ManageUsersView";
        window.open(openUrl, "_self");
    }

    render() {
        let usrname = window.location.href.split('/')[3];
        let basicUrl = "/" + usrname;

        if (this.state.userRole == 0)
            return (
                <div className="sideBar">
                    <ul>
                        <Link to={basicUrl + "/HomeView"}>
                            <li><img src={bookIcon}/> </li>
                        </Link>
                        <Link to={basicUrl + "/CartView"}>
                            <li><img src={cartIcon}/></li>
                        </Link>
                        <Link to={basicUrl + "/OrderView"}>
                            <li><img src={orderIcon}/></li>
                        </Link>
                        <li><img src={profileIcon}/> </li>
                    </ul>
                </div>
            );
        if (this.state.userRole == 1)
            return (
                <div className="sideBar">
                    <ul>
                        <Link to={basicUrl + "/HomeView"}>
                            <li><img src={bookIcon}/> </li>
                        </Link>
                        <li onClick={this.handleClickAddBook}><h3>AddBook</h3></li>
                        <li onClick={this.handleClickManageUsers}><h3>ManageUsers</h3></li>
                    </ul>
                </div>
            );
    }
}
