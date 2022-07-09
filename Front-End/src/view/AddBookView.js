import React from "react";
import {HeadBar} from "../components/HeadBar";
import {SideBar} from "../components/SideBar";
import {ClassifyBox, SearchBox} from "../components/SearchBox";
import {BookCarousel} from "../components/Carousel";
import {BookExcel} from "../components/BookExcel";
import {AddBookForm} from "../components/AddBookForm";

export class AddBookView extends React.Component {

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
                    <AddBookForm/>
                </section>
            </div>
        );
    }

}