import React from "react";

import {HeadBar} from "../components/HeadBar";
import {SideBar} from "../components/SideBar";
import {CartItem} from "../components/CartItem";
import "../css/cart.css"

export class OrderView extends React.Component {

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
                    <div className="cartItmExcel">
                        <CartItem btnName="More Info"/>
                    </div>
                </section>
            </div>
        );
    }
}