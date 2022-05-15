import React from "react";

import {HeadBar} from "../components/HeadBar";
import {SideBar} from "../components/SideBar";
import {OrderExcel} from "../components/OrderExcel";
import "../css/order.css"

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
                    <div className="orderItmExcel">
                        <OrderExcel/>
                    </div>
                </section>
            </div>
        );
    }
}