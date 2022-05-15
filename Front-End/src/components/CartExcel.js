import React, {Component} from "react";
import "../css/cart.css"
import {deleteAllCart, deleteCart, getCart} from "../services/cartService";
import {Link} from "react-router-dom";
import {createMultipleOrders, createOrder} from "../services/orderService";

export class CartExcel extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            username: window.location.href.split('/')[3],
            books: []
        }
    }

    componentDidMount() {
        this.refreshPage();
    }

    refreshPage() {
        let data = {"username": this.state.username};
        const callback = (data) => {
            this.setState({books: data});
        }
        getCart(data, callback);
    }

    hanleClick = (e) => {
        const bookid = e.target.dataset.key;
        const data = {"bookid": bookid, "username": this.state.username};
        createOrder(data);
        deleteCart(data);
        this.refreshPage();
    }

    buyAll = (e) => {
        e.preventDefault();
        const data = {"username": this.state.username};
        createMultipleOrders(data);
        deleteAllCart(data);
        this.refreshPage();
    }

    render() {
        const basicRoute = "/" + this.state.username + "/BookView";
        return(
            <div className="cartItemExcel">
                <div className="cartButtonArea">
                    <input type="button" value="Buy All" onClick={this.buyAll}/>
                </div>
                {this.state.books.map((book, idx) =>
                    <div className="cartItem" data-key={book["bookID"]}>
                        <div className="cartItemImage">
                            <Link to={basicRoute + "/" + book["bookID"]}>
                                <img src={book["image"]}/>
                            </Link>
                        </div>
                        <div className="cartItemName">
                            <h3>{book["bookName"]}</h3>
                        </div>
                        <div className="cartItemPrice">
                            <h2>￥{book["price"]}</h2>
                        </div>
                        <div className="cartItemBtn">
                            <input type="button" data-key={book["bookID"]}
                                   onClick={this.hanleClick} value="Buy Now"/>
                        </div>
                    </div>
                )}
            </div>
            )
    }
}