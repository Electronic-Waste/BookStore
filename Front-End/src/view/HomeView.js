import React from "react";

import {HeadBar} from "../components/HeadBar";
import {SideBar} from "../components/SideBar";
import {ClassifyBox, SearchBox} from "../components/SearchBox";
import {BookCarousel} from "../components/Carousel";
import {BookExcel} from "../components/BookExcel";

export const headers = ["Name", "Author", "Type", "Price", "Description", "Inventory", "Image"];

export const data = [
    ["Le Petit Prince (The Little Prince)", "Antoine de Saint-Exupéry", "World Master Pieces", "8.89", "Introduction remains to be added", "50","http://img3m9.ddimg.cn/75/6/25067469-1_u_2.jpg"],
    ["Harry Potter and the Philosopher's Stone", "J. K. Rowling", "Magic Novels", "30.20", "Introduction remains to be added", "60", "http://img3m1.ddimg.cn/88/0/25479421-1_w_1.jpg"],
    ["Dream of the Red Chamber", "Cao Xueqin", "World Master Pieces", "18.80", "Introduction remains to be added", "70", "http://img3m6.ddimg.cn/31/22/23828836-1_w_8.jpg"],
    ["Effecive C++", "Scott Meyers", "Coding", "53.30", "Introduction remains to be added", "30", "http://img3m6.ddimg.cn/96/25/21000966-1_u_12.jpg"],
    ["The Old Man and the Sea", "Ernest Miller Hemingway", "World Master Pieces", "27.80", "Introduction remains to be added", "60", "http://img3m6.ddimg.cn/94/11/27891166-1_u_2.jpg"],
    ["Data System Concepts", "Abraham Silberschatz", "Coding", "74.20", "Introduction remains to be added", "20", "http://img3m2.ddimg.cn/83/5/22632572-1_w_1.jpg"]
];

export class HomeView extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            dataOnDisplay: data
        }
        this.search = this.search.bind(this);
    }

    search(e) {
        let filterText = e.target.value.toLowerCase();
        let dataSlice = data.filter(function (row) {
            return row[0].toString().toLowerCase().indexOf(filterText) > -1;
        });
        this.setState({dataOnDisplay: dataSlice});
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
                        <ClassifyBox/>
                    </div>
                    <div className="mid_area">
                        <BookCarousel/>
                    </div>
                    <div className="down_area">
                        <BookExcel books={this.state.dataOnDisplay}/>
                    </div>
                </section>
            </div>
        );
    }
}
