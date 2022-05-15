import {postRequest, postRequest_noRet} from "../utils/ajax";

export const addToCart = (data) => {
    const url = "http://localhost:8080/addtocart";
    postRequest_noRet(url, data);
    alert("The book is added to your cart");
}

export const getCart = (data, callback) => {
    const url = "http://localhost:8080/getcart";
    postRequest(url, data, callback);
}

export const deleteCart = (data) => {
    const url = "http://localhost:8080/delfromcart";
    postRequest_noRet(url, data);
    alert("You bought a book! Check it in \"Order\" view");
}

export const deleteAllCart = (data) => {
    const url = "http://localhost:8080/delallfromcart";
    postRequest_noRet(url, data);
    alert("You bought all books! Check it in \"Order\" view");
}