import React from "react";

import "../css/bookDetail.css"
import purchaseIcon from "../assets/purchase.svg"
import cartIcon from "../assets/cart.svg"
import {data, headers} from "../view/HomeView";

export class BookDetail extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            dataSource: data[this.props.index],
            editIndex: -1
        }

    }

    showEditor = (e) => {
        this.setState({
            editIndex: parseInt(e.target.dataset.key, 10)
        })
        console.log(this.state.editIndex);
        console.log(1);
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
        let content = this.state.dataSource[idx];
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
                    <img src={this.state.dataSource[6]}/>
                </div>
                <div className="book_description">
                    <table>
                        <tr>{this.createItem(0)}</tr>
                        <tr>{this.createItem(1)}</tr>
                        <tr>{this.createItem(2)}</tr>
                        <tr>{this.createItem(3)}</tr>
                        <tr>{this.createItem(5)}</tr>
                        <tr>{this.createItem(4)}</tr>
                    </table>
                </div>
            </div>
        );
    }
}

export class ButtonForPurchase extends React.Component {

    render() {
        return (
            <div className="button_group">
                <img src={purchaseIcon}/>
                <img src={cartIcon}/>
            </div>
        );
    }
}

