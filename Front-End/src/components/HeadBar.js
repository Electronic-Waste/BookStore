import React from "react";
import "../css/home.css"
import logoImg from "../assets/Java_logo.jfif"
import logoText from "../assets/bookStoreText.svg"
import {Link} from "react-router-dom";
import {postRequest} from "../utils/ajax";

export class Logo extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        let basicUrl = "/" + this.props.id;
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

        this.state = {
            username: ""
        }
    }

    componentDidMount() {
        let id = this.props.id;
        let data = {"userId": id};
        let url = "http://localhost:8080/bookstore/getUser";
        const callback = (data) => {
            this.setState({
                username: data.data.username
            })
        }
        postRequest(url, data, callback);
    }

    render() {
        return (
            <div className="greet">
                <p className="name">Hi, {this.state.username}!</p>
            </div>
        );
    }
}

export class HeadBar extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        let userId = window.location.href.split('/')[3];
        return (
            <div className="headBar">
                <Logo id={userId}/>
                <UserIcon id={userId}/>
                <div style={{clear: "both"}}></div>
            </div>
        );
    }
}
