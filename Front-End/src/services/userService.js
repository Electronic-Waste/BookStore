import {postRequest} from "../utils/ajax";


export const Login = (data, navigate) => {
    let url = "http://localhost:8080/login";
    console.log("UserService YES");
    const callback = (data) => {
        if (data.status >= 0) {
            let username = data.data.UserName;
            let navigateUrl = "/" + username + "/HomeView";
            navigate(navigateUrl);
        }
        else {
            alert("Account or password is incorrect!");
        }
    }
    postRequest(url, data, callback);
}