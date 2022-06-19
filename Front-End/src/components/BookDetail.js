import React from "react";

import "../css/bookDetail.css"
import purchaseIcon from "../assets/purchase.svg"
import cartIcon from "../assets/cart.svg"
import {headers} from "../view/HomeView";
import {getBook} from "../services/bookService";
import {addToCart} from "../services/cartService";

export class BookDetail extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            dataSource: [],
            book: "",
            editIndex: -1
        }

    }

    componentDidMount() {
        let id = window.location.href.split('/')[5];
        let bookID = {"id": id};
        //console.log(bookID);
        const callback = (data) => {
            this.setState({
                book: data
            });
        }
        getBook(bookID, callback);

    }

    showEditor = (e) => {
        this.setState({
            editIndex: parseInt(e.target.dataset.key, 10)
        })
        // console.log(this.state.editIndex);
        // console.log(1);
    }

    save = (e) => {
        e.preventDefault();
        let input = e.target.firstChild;
        let editIndex = this.state.editIndex;
        let data = this.state.dataSource;
        data[editIndex] = input.value;
        this.setState({
            editIndex: -1,
            dataSource: data
        });
    }

    createItem(idx) {
        let key = headers[idx];
        key = key.replace(key[0], key[0].toLowerCase());
        let content = this.state.book[key];
        let editIndex = this.state.editIndex;
        if (editIndex >= 0 && editIndex == idx) {
            content = (
                <form onSubmit={this.save}>
                    <input type="text" defaultValue={content}/>
                </form>
            );
        }
        return <td data-key={idx} onDoubleClick={this.showEditor}>
                {headers[idx]}: {content}
            </td>;
    }

    render = () => {
        return (
            <div className="book_details">
                <div className="book_img">
                    <img src={this.state.book.image}/>
                </div>
                <div className="book_description">
                    <table>
                        <tbody>
                        <tr>{this.createItem(1)}</tr>
                        <tr>{this.createItem(2)}</tr>
                        <tr>{this.createItem(3)}</tr>
                        <tr>{this.createItem(4)}</tr>
                        <tr>{this.createItem(6)}</tr>
                        <tr>{this.createItem(5)}</tr>
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export class ButtonForPurchase extends React.Component {
    handleClickCart = e => {
        const userId = window.location.href.split('/')[3];
        const bookId = window.location.href.split('/')[5];
        console.log(userId + " " + bookId);
        let data = {"userId": userId, "bookId": bookId};
        console.log(data);
        addToCart(data);
    }

    render() {
        return (
            <div className="button_group">
                <img src={purchaseIcon}/>
                <img src={cartIcon} onClick={this.handleClickCart}/>
            </div>
        );
    }
}

