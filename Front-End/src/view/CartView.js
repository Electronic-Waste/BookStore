import React from "react";

import {HeadBar} from "../components/HeadBar";
import {SideBar} from "../components/SideBar";
import {CartExcel} from "../components/CartExcel";
import "../css/cart.css"
import {deleteAllCart} from "../services/cartService";

export class CartView extends React.Component {

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
                    <CartExcel/>
                </section>
            </div>
        );
    }
}