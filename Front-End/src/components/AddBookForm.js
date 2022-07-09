import React from "react";
import {postRequest_noRet} from "../utils/ajax";
import {Button, Form, Input} from "antd";
import "../css/addBook.css"

export class AddBookForm extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            userId: window.location.href.split('/')[3]
        }
    }

    handleClick = (values) => {
        console.log(values);
        // let bookname = document.getElementById("add-book-bookname").value.toString();
        // let author = document.getElementById("add-book-author").value.toString();
        // let type = document.getElementById("add-book-type").value.toString();
        // let price = document.getElementById("add-book-price").value.toString();
        // let description = document.getElementById("add-book-description").value.toString();
        // let inventory = document.getElementById("add-book-inventory").value.toString();
        // let image = document.getElementById("add-book-image").value.toString();
        // const data = {"bookname": bookname, "author": author, "type": type, "price": price,
        //                     "description": description, "inventory": inventory, "image": image};
        let url = "http://localhost:8080/addBook";
        postRequest_noRet(url, values);
        alert("The book is added to the database!");
        let openUrl = "http://localhost:3000/" + this.state.userId + "/HomeView";
        window.open(openUrl, "_self");
    }

    render() {
        // return (
        //     <div className="add-book-form">
        //         <input type="text" id="add-book-bookname" placeholder="Book Name"/>
        //         <input type="text" id="add-book-author" placeholder="Author"/>
        //         <input type="text" id="add-book-type" placeholder="Type"/>
        //         <input type="text" id="add-book-price" placeholder="Price"/>
        //         <input type="text" id="add-book-description" placeholder="Description"/>
        //         <input type="text" id="add-book-inventory" placeholder="Inventory"/>
        //         <input type="text" id="add-book-image" placeholder="Image"/>
        //         <input type="button" onClick={this.handleClick} value="Commit"/>
        //     </div>
        // );
        return (
            <Form
                name="book-from"
                className="add-book-form"
                onFinish={this.handleClick}>
                <Form.Item
                    label="Book Name"
                    name="bookname"
                    rules={[
                        {
                            required: true,
                            message: 'Please input the book name!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    label="Author"
                    name="author"
                    rules={[
                        {
                            required: true,
                            message: 'Please input the author!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    label="Type"
                    name="type"
                    rules={[
                        {
                            required: true,
                            message: 'Please input the type!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    label="Price"
                    name="price"
                    rules={[
                        {
                            required: true,
                            message: 'Please input the price!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    label="Description"
                    name="description"
                    rules={[
                        {
                            required: true,
                            message: 'Please input the description!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    label="Inventory"
                    name="inventory"
                    rules={[
                        {
                            required: true,
                            message: 'Please input the inventory!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    label="Image"
                    name="image"
                    rules={[
                        {
                            required: true,
                            message: 'Please input the image!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item style={{textAlign: "center"}}>
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                </Form.Item>
            </Form>
        )
    }
}
