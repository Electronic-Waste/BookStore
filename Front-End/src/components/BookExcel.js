import React from "react";

import "../css/home.css"
import {Link} from "react-router-dom";


export class BookExcel extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        let bookArr = this.props.books;
        let userId = window.location.href.split('/')[3];
        let url = "/" + userId + "/BookView/";
        //console.log(bookArr);
        return(
            <div className="bookList">
                {bookArr.map((book, idx) => (
                    <div className="bookDisplay" key={book.bookId}>
                        <Link to={url + (book.bookId)}>
                            <img src={book.image}/>
                        </Link>
                    </div>
                ))}
            </div>
        );
    }
}
