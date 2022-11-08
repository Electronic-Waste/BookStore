import React from "react";

import {HeadBar} from "../components/HeadBar";
import {SideBar} from "../components/SideBar";
import {ClassifyBox, SearchBox} from "../components/SearchBox";
import {BookCarousel} from "../components/Carousel";
import {BookExcel} from "../components/BookExcel";
import {getBooks, getBookWithFullTextSearch} from "../services/bookService";

export const headers = ["bookId", "bookname", "author", "type", "price", "description", "inventory", "image"];

export class HomeView extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            booksOnDisplay: [],
            books: []
        }
        this.search = this.search.bind(this);
    }

    componentDidMount() {
        const callback = (data) => {
            this.setState({
                booksOnDisplay: data,
                books: data
            })
        }
        getBooks({"search": null}, callback);
    }


    search(e) {
        let filterText = document.getElementById("search-bar").value;
        // console.log(filterText);
        // console.log(this.state.books);
        let isBookName = document.getElementById("search-switch").ariaChecked.toString();
        console.log(isBookName);
        let dataSlice = [];
        if (isBookName == "true") {
            dataSlice = this.state.books.filter(function (row) {
                return row.bookname.toString().toLowerCase().indexOf(filterText) > -1;
            });
            this.setState({booksOnDisplay: dataSlice});
        }
        else {
            let data = {"filterText": filterText};
            const callback = (retData) => {
                this.setState({booksOnDisplay: retData});
            }
            getBookWithFullTextSearch(data, callback);      // Get data from solr
        }

    }

    render() {
        return (
            <div>
                <header>
                    <HeadBar/>
                </header>
                <aside>
                    <SideBar/>
                </aside>
                <section>
                    <div className="up_area">
                        <SearchBox search={this.search}/>
                    </div>
                    <div className="mid_area">
                        <BookCarousel/>
                    </div>
                    <div className="down_area">
                        <BookExcel books={this.state.booksOnDisplay}/>
                    </div>
                </section>
            </div>
        );
    }
}
