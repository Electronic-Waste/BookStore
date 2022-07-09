import React from "react";
import {LoginView} from "./view/LoginView";
import {HomeView} from "./view/HomeView";
import {BookView} from "./view/BookView";
import {CartView} from "./view/CartView";
import {OrderView} from "./view/OrderView";
import {Router, Route, Routes} from "react-router"
import {BrowserRouter} from "react-router-dom"
import {AddBookForm} from "./components/AddBookForm";
import {AddBookView} from "./view/AddBookView";
import {UserManageView} from "./view/UserManageView";
import {RegisterView} from "./view/RegisterView";

export class BasicRoute extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<LoginView/>}/>
                    <Route path="/register" element={<RegisterView/>}/>
                    <Route path="/:userid/HomeView" element={<HomeView/>}/>
                    <Route path="/:userid/BookView/:name" element={<BookView/>}/>
                    <Route path="/:userid/CartView" element={<CartView/>}/>
                    <Route path="/:userid/OrderView" element={<OrderView/>}/>
                    <Route path="/:userid/AddBookView" element={<AddBookView/>}/>
                    <Route path="/:userid/ManageUsersView" element={<UserManageView/>}/>
                </Routes>
            </BrowserRouter>
        );
    }
}

