import React from "react";
import "../css/login.css"
import {Login} from "../services/userService";
import {Link} from "react-router-dom";
import {useNavigate} from "react-router";


export function LoginForm ()
{
    const navigate = useNavigate();

    const handleLogin = e => {
        const account = document.getElementById("account").value;
        const password = document.getElementById("password").value;
        let userInfo = {"UserID" : account, "Password" : password};
        //console.log(userInfo);
        Login(userInfo, navigate);
    }


    return (
        <form name="Login" method="get" className="loginForm">
            <input type="text" id="account" placeholder="Account"/>
            <br/>
            <input type="password" id="password" placeholder="Password"/>
            <br/><br/>
            <div style={{textAlign: "center"}}>
                <input type="button" value="Login" onClick={handleLogin}/>
            </div>
        </form>
        );

}