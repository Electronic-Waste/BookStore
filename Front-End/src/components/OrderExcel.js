import React from "react";
import {Link} from "react-router-dom";
import {getOrders} from "../services/orderService";

export class OrderExcel extends React.Component
{
    constructor(props) {
        super(props);

        this.state = {
            userId: window.location.href.split('/')[3],
            orders: []
        }
    }

    componentDidMount() {
        this.refresh();
    }

    hanleClick = (e) => {

    }

    refresh = () => {
        const data = {"userId": this.state.userId};
        const callback = (data) => {
            this.setState({
                orders: data.data.orders
            })
        }
        getOrders(data, callback);
    }

    render() {
        return (
            <div className="orderExcel">
                {this.state.orders.map((order, idx) =>
                    <div className="order" key={order["orderId"]}>
                        <div className="orderID">
                            <h3>OrderID: {order["orderId"]}</h3>
                        </div>
                        <div className="orderPrice">
                            <h2>Total Price: ￥{order["price"]}</h2>
                        </div>
                        <div className="orderBtn">
                            <input type="button" data-key={order["orderId"]}
                                   onClick={this.hanleClick} value="More Info"/>
                        </div>
                    </div>
                )}
            </div>
        );
    }

}