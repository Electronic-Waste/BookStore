import React from "react";

import "../css/home.css"
import {Link} from "react-router-dom";


export class BookExcel extends React.Component {
    constructor(props) {
        super(props);

    }

    render() {
        let bookArr = this.props.books;
        return(
            <div className="bookList">
                {bookArr.map((book, idx) => (
                    <div className="bookDisplay">
                        <Link to={"/BookView/" + (idx)}>
                            <img key={idx} src={book[6]}/>
                        </Link>
                    </div>
                ))}
            </div>
        );
    }
}
