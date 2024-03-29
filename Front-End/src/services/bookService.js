import {postRequest, postRequest_noRet} from "../utils/ajax";

export const getBooks = (data, callback) => {
    const url = "http://localhost:8080/bookstore/getBooks";
    postRequest(url, data, callback);
}

export const getBook = (id, callback) => {
    const url = "http://localhost:8080/bookstore/getBook";
    postRequest(url, id, callback);
};

export const deleteBook = (bookId) => {
    const url = "http://localhost:8080/bookstore/deleteBook";
    const data = {"id": bookId};
    postRequest_noRet(url, data);
    alert("You have deleted the book!");
    let userId = window.location.href.split('/')[3];
    window.open("/" + userId + "/HomeView", "_self");
}

export const getBookWithFullTextSearch = (data, callback) => {
    const url = "http://localhost:8080/bookstore/solr/search";
    postRequest(url, data, callback);
}
