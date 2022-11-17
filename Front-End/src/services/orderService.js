import {postRequest, postRequest_noRet} from "../utils/ajax";

export const createOrder = (data, carts) => {
    const url = "http://localhost:8080/bookstore/oneorder";
    const orderServiceUrl = "http://localhost:8080/order-service/get-order-sum";
    const callback = (data) => {
        alert("Total price is: " + data.price);
    }
    const cartData = {"carts": carts};
    console.log(cartData);
    postRequest(orderServiceUrl, cartData, callback);
    postRequest_noRet(url, data);
    alert("You bought a book! Check it in \"Order\" view");
}

export const createMultipleOrders = (data, carts) => {
    const url = "http://localhost:8080/bookstore/multipleorders";
    const orderServiceUrl = "http://localhost:8080/order-service/get-order-sum";
    const callback = (data) => {
        alert("Total price is: " + data.price);
    }
    const cartData = {"carts": carts};
    console.log(cartData);
    postRequest(orderServiceUrl, cartData, callback);
    postRequest_noRet(url, data);
    alert("You bought all books! Check it in \"Order\" view");
}

export const getOrders = (data, callback) => {
    const url = "http://localhost:8080/bookstore/getorders";
    postRequest(url, data, callback);
}

export const getOrderItems = (data, callback) => {
    const url = "http://localhost:8080/bookstore/getorderitems";
    postRequest(url, data, callback);
}
