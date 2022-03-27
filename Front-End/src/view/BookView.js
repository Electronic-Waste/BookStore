import React from "react";

import {HeadBar} from "../components/HeadBar";
import {SideBar} from "../components/SideBar";
import {BookDetail, ButtonForPurchase} from "../components/BookDetail"
import "../css/bookDetail.css"
import {useParams} from "react-router";

export function BookView() {
    let index_string = useParams();
    let index = index_string.name;

        return (
            <div>
                <header>
                    <HeadBar/>
                </header>
                <aside>
                    <SideBar/>
                </aside>
                <section>
                    <div className="content">
                        <BookDetail index={index}/>
                        <ButtonForPurchase/>
                    </div>
                </section>
            </div>

        );

}
