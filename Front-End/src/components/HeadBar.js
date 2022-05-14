import React from "react";
import "../css/home.css"
import logoImg from "../assets/Java_logo.jfif"
import logoText from "../assets/bookStoreText.svg"
import {Link} from "react-router-dom";

export class Logo extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        let basicUrl = "/" + this.props.name;
        return (
        <div id="logo">
            <Link to={basicUrl + "/HomeView"}>
                <img id="logoImg" src={logoImg}/>
                <img id="logoText" src={logoText}/>
            </Link>
        </div>
        );
    }
}

export class UserIcon extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className="greet">
                <p className="name">Hi, {this.props.name}!</p>
            </div>
        );
    }
}

export class HeadBar extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        let usrname = window.location.href.split('/')[3];
        return (
            <div className="headBar">
                <Logo name={usrname}/>
                <UserIcon name={usrname}/>
                <div style={{clear: "both"}}></div>
            </div>
        );
    }
}
