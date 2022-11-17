import React, {useEffect, useState} from "react";

import "../css/home.css"
import bookIcon from "../assets/books.png"
import cartIcon from "../assets/mycart.png"
import  orderIcon from "../assets/myorders.png"
import profileIcon from "../assets/myprofile.png"
import {Link} from "react-router-dom";
import {postRequest} from "../utils/ajax";
import {useNavigate} from "react-router";
import userEvent from "@testing-library/user-event";

// export class SideBar extends React.Component {
//     constructor(props) {
//         super(props);
//
//         this.state = {
//             userId: window.location.href.split('/')[3],
//             userRole: 0
//         }
//     }
//
//     componentDidMount() {
//         let data = {"userId": this.state.userId};
//         let url = "http://localhost:8080/getUser";
//         const callback = (data) => {
//             let role = data.data.role;
//             this.setState({
//                 userRole: role
//             })
//         }
//         postRequest(url, data, callback);
//     }
//
//     handleClickAddBook = (e) => {
//         let openUrl = "http://localhost:3000/" + this.state.userId + "/AddBookView";
//         window.open(openUrl, "_self");
//     }
//
//     handleClickManageUsers = (e) => {
//         let openUrl = "http://localhost:3000/" + this.state.userId + "/ManageUsersView";
//         window.open(openUrl, "_self");
//     }
//
//     render() {
//         let usrname = window.location.href.split('/')[3];
//         let basicUrl = "/" + usrname;
//
//         if (this.state.userRole == 0)
//             return (
//                 <div className="sideBar">
//                     <ul>
//                         <Link to={basicUrl + "/HomeView"}>
//                             <li><img src={bookIcon}/> </li>
//                         </Link>
//                         <Link to={basicUrl + "/CartView"}>
//                             <li><img src={cartIcon}/></li>
//                         </Link>
//                         <Link to={basicUrl + "/OrderView"}>
//                             <li><img src={orderIcon}/></li>
//                         </Link>
//                         <li><img src={profileIcon}/> </li>
//                     </ul>
//                 </div>
//             );
//         if (this.state.userRole == 1)
//             return (
//                 <div className="sideBar">
//                     <ul>
//                         <Link to={basicUrl + "/HomeView"}>
//                             <li><img src={bookIcon}/> </li>
//                         </Link>
//                         <li onClick={this.handleClickAddBook}><h3>AddBook</h3></li>
//                         <li onClick={this.handleClickManageUsers}><h3>ManageUsers</h3></li>
//                     </ul>
//                 </div>
//             );
//     }
// }

export function SideBar (props) {
    const navigate = useNavigate();
    const userId = window.location.href.split('/')[3];
    const [role, setRole] = useState(0);
    const basicUrl = "/" + userId;

    useEffect(() => {
        let data = {"userId": userId};
        let url = "http://localhost:8080/bookstore/getUser";
        const callback = (data) => {
            let role = data.data.role;
            setRole(role);
        }
        postRequest(url, data, callback);
    }, [])

    const handleClickBooks = (e) => {
        let openUrl = basicUrl + "/HomeView";
        navigate(openUrl);
    }

    const handleClickCart = (e) => {
        let openUrl = basicUrl + "/CartView";
        navigate(openUrl);
    }

    const handleClickOrder = (e) => {
        let openUrl = basicUrl + "/OrderView";
        navigate(openUrl);
    }
    const handleClickAddBook = (e) => {
        let openUrl = basicUrl + "/AddBookView";
        navigate(openUrl);
    }

    const handleClickManageUsers = (e) => {
        let openUrl = basicUrl + "/ManageUsersView";
        navigate(openUrl);
    }

    const handleClickSignOut = (e) => {
        let url = "http://localhost:8080/bookstore/logout";
        let callback = (data) => {
            console.log(data);
            let interval = data.data.interval;
            console.log("用户在线时长: " + interval + "s");
        }
        let data = null;
        postRequest(url, data, callback);
        navigate("/");
    }

    if (role == 0)
        return (
            <div className="sideBar">
                <ul>
                    <li onClick={handleClickBooks}><h3>Books</h3></li>
                    <li onClick={handleClickCart}><h3>My Cart</h3></li>
                    <li onClick={handleClickOrder}><h3>My Orders</h3></li>
                    {/*<li><h3>My Profile</h3></li>*/}
                    <li onClick={handleClickSignOut}><h3>Sign Out</h3></li>
                </ul>
            </div>
        );
    else if (role == 1)
        return (
            <div className="sideBar">
                <ul>
                    <li onClick={handleClickBooks}><h3>Books</h3></li>
                    <li onClick={handleClickAddBook}><h3>AddBook</h3></li>
                    <li onClick={handleClickManageUsers}><h3>ManageUsers</h3></li>
                    <li onClick={handleClickSignOut}><h3>Sign Out</h3></li>
                </ul>
            </div>
        );

}
