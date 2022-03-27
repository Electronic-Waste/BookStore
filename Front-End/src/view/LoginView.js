import React from "react";
import "../css/login.css"
import {LoginForm} from "../components/LoginForm"

export class LoginView extends React.Component {
    
    render() {
        return (
            <div className="login-bgd">
                <div className="login-box">
                    <h2>Login</h2>
                    <LoginForm/>
                </div>
            </div>

        );
    }
}
