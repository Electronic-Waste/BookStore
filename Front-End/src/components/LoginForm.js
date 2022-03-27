import React from "react";
import "../css/login.css"
import {Link} from "react-router-dom";


export class LoginForm extends React.Component {
    constructor(props) {
        super(props);

    }


    render() {
        return (
            <form name="Login" method="get" className="loginForm">
                <input type="text" name="account" placeholder="Account"/>
                <br/>
                <input type="password" name="password" placeholder="Password"/>
                <br/><br/>
                <div style={{textAlign: "center"}}>
                    <Link to="/HomeView">
                        <input type="submit" value="Submit" />
                    </Link>
                </div>
            </form>
        );
    }
}