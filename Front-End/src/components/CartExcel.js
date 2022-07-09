import React, {Component} from "react";
import "../css/cart.css"
import {deleteAllCart, deleteCart, getCart} from "../services/cartService";
import {Link} from "react-router-dom";
import {createMultipleOrders, createOrder} from "../services/orderService";
import {Button} from "antd";

export class CartExcel extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            userId: window.location.href.split('/')[3],
            carts: []
        }
    }

    componentDidMount() {
        this.refreshPage();
    }

    refreshPage() {
        let data = {"userId": this.state.userId};
        const callback = (data) => {
            this.setState({carts: data.data.carts});
        }
        getCart(data, callback);
    }

    hanleClick = (e) => {
        const bookId = e.target.dataset.key;
        const data = {"bookId": bookId, "userId": this.state.userId};
        createOrder(data);
        deleteCart(data);
        this.refreshPage();
    }

    buyAll = (e) => {
        e.preventDefault();
        const data = {"userId": this.state.userId};
        createMultipleOrders(data);
        deleteAllCart(data);
        this.refreshPage();
    }

    render() {
        const basicRoute = "/" + this.state.userId + "/BookView";
        return(
            <div className="cartItemExcel">
                {this.state.carts.map((cart, idx) =>
                    <div className="cartItem" data-key={cart.book["bookId"]}>
                        <div className="cartItemImage">
                            <Link to={basicRoute + "/" + cart.book["bookId"]}>
                                <img src={cart.book["image"]}/>
                            </Link>
                        </div>
                        <div className="cartItemName">
                            <h3>{cart.book["bookname"]}</h3>
                        </div>
                        <div className="cartItemPrice">
                            <h2 style={{color: "red"}}>￥{cart.book["price"] * cart.num}</h2>
                        </div>
                        <div className="cartItemNum">
                            <h2>{cart.num}</h2>
                        </div>
                        <div className="cartItemBtn">
                            <input type="button" data-key={cart.book["bookId"]}
                                   onClick={this.hanleClick} value="Buy Now"/>
                        </div>
                    </div>
                )}
                <div className="cartButtonArea">
                    <Button type="primary" onClick={this.buyAll} danger>Buy All!</Button>
                </div>
            </div>
            )
    }
}
