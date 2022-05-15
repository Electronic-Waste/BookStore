import React from "react";

import {HeadBar} from "../components/HeadBar";
import {SideBar} from "../components/SideBar";
import {ClassifyBox, SearchBox} from "../components/SearchBox";
import {BookCarousel} from "../components/Carousel";
import {BookExcel} from "../components/BookExcel";
import {getBooks} from "../services/bookService";

export const headers = ["BookID", "BookName", "Author", "Type", "Price", "Description", "Inventory", "Image"];

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
        let filterText = e.target.value.toLowerCase();
        //console.log(filterText);
        let dataSlice = this.state.books.filter(function (row) {
            return row.bookName.toString().toLowerCase().indexOf(filterText) > -1;
        });
        this.setState({booksOnDisplay: dataSlice});
    }

    classify = e => {
        let filterText = e.target.dataset.type.replace("#", "").toLowerCase();
        //console.log(filterText);
        let dataSlice = this.state.books.filter(function (row) {
            return row.type.toString().toLowerCase().indexOf(filterText) > -1;
        });
        this.setState({booksOnDisplay: dataSlice});
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
                        <ClassifyBox classify={this.classify}/>
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
