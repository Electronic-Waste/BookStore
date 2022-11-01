import {postRequest, postRequest_noRet} from "../utils/ajax";

export const createOrder = (data) => {
    const url = "http://localhost:8080/oneorder";
    postRequest_noRet(url, data);
    alert("You bought a book! Check it in \"Order\" view");
}

export const createMultipleOrders = (data) => {
    const url = "http://localhost:8080/multipleorders";
    postRequest_noRet(url, data);
    alert("You bought all books! Check it in \"Order\" view");
}

export const getOrders = (data, callback) => {
    const url = "http://localhost:8080/getorders";
    postRequest(url, data, callback);
}

export const getOrderItems = (data, callback) => {
    const url = "http://localhost:8080/getorderitems";
    postRequest(url, data, callback);
}
