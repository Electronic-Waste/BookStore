import {postRequest} from "../utils/ajax";

export const getBooks = (data, callback) => {
    const url = "http://localhost:8080/getBooks";
    postRequest(url, data, callback);
}

export const getBook = (id, callback) => {
    const url = "http://localhost:8080/getBook";
    postRequest(url, id, callback);
};