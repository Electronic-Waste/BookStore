import React from "react";

import "../css/home.css"
import bookIcon from "../assets/books.png"
import cartIcon from "../assets/mycart.png"
import  orderIcon from "../assets/myorders.png"
import profileIcon from "../assets/myprofile.png"
import {Link} from "react-router-dom";

export class SideBar extends React.Component {

    render() {
        return (
            <div className="sideBar">
                <ul>
                    <Link to="/HomeView">
                        <li><img src={bookIcon}/> </li>
                    </Link>
                    <Link to="/CartView">
                        <li><img src={cartIcon}/></li>
                    </Link>
                    <Link to="/OrderView">
                        <li><img src={orderIcon}/></li>
                    </Link>
                    <li><img src={profileIcon}/> </li>
                </ul>
            </div>
        );
    }
}
