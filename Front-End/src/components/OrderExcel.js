import React from "react";
import {Link} from "react-router-dom";
import {getOrders} from "../services/orderService";
import {SearchBox} from "./SearchBox";
import {getBooks} from "../services/bookService";

export class OrderExcel extends React.Component
{
    constructor(props) {
        super(props);

        this.state = {
            userId: window.location.href.split('/')[3],
            orders: [],
            books: []
        }
    }

    componentDidMount() {
        this.refresh();
        const callback = (data) => {
            this.setState({
                books: data
            })
        }
        getBooks({"search": null}, callback);
    }

    handleClick = (e) => {
        /* Get BookId List */
        let filterText = document.getElementById("search-bar").value;
        let dataSlice = this.state.books.filter(function (row) {
            return row.bookname.toString().toLowerCase().indexOf(filterText) > -1;
        });
        let bookIdList = dataSlice.map((book) => book.bookId)

        /* Get orders according to bookId */
        let newOrders = this.state.orders.map((order, idx) => {

            return order;
        })
        this.setState({
            orders: newOrders
        })
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
                <div className="orderExcel-search">
                    <SearchBox search={this.handleClick}/>
                </div>
                <div className="orderExcel-content">
                    {this.state.orders.map((order, idx) =>
                        <div className="order" key={order["orderId"]}>
                            <div className="orderID">
                                <h3 className="orderID-id">OrderID: {order["orderId"]}</h3>
                            </div>
                            <div className="orderPrice">
                                <h2 style={{color:"red"}}>Total Price: ￥{order["price"]}</h2>
                            </div>
                            <div className="orderBtn">
                                <input type="button" data-key={order["orderId"]} value="More Info"/>
                            </div>
                        </div>
                    )}
                </div>
            </div>
        );
    }

}
