import React from "react";
import "../css/login.css"
import {LoginForm, RegisterForm} from "../components/LoginForm"

export class RegisterView extends React.Component {

    render() {
        return (
            <div className="login-bgd">
                <div className="login-box">
                    <h2>Register</h2>
                    <RegisterForm/>
                </div>
            </div>

        );
    }
}
