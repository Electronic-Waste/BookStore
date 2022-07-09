import {postRequest} from "../utils/ajax";


export const login = (data, navigate) => {
    let url = "http://localhost:8080/login";
    const callback = (data) => {
        if (data.status >= 0 && data.data.role >= 0) {
            let userId = data.data.userId;
            let navigateUrl = "/" + userId + "/HomeView";
            navigate(navigateUrl);
        }
        else if (data.status >= 0 && data.data.role < 0) {
            alert("This account is banned!");
        }
        else {
            alert("Account or password is incorrect!");
        }
    }
    postRequest(url, data, callback);
}

export const register = (data) => {
    let url = "http://localhost:8080/register";
    const callback = (data) => {
        if (data.status >= 0) {
            let userId = data.data.userId;
            let password = data.data.password;
            alert("Register Successfully! Your account is: " + userId + " Your password is: " + password);
            window.open("/", "_self");
        }
        else {
            alert("Error: Username must be different!");
        }
    }
    postRequest(url, data, callback)
}

export const getUsers = (callback) => {
    const url = "http://localhost:8080/getUsers";
    const data = {"search": null};
    postRequest(url, data, callback);
}

export const banUser = (data, callback) => {
    const url = "http://localhost:8080/banUser";
    postRequest(url, data, callback);
}
