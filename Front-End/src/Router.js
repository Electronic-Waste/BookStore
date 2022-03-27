import React from "react";
import {LoginView} from "./view/LoginView";
import {HomeView} from "./view/HomeView";
import {BookView} from "./view/BookView";
import {CartView} from "./view/CartView";
import {OrderView} from "./view/OrderView";
import {Router, Route, Routes} from "react-router"
import {BrowserRouter} from "react-router-dom"

export class BasicRoute extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<LoginView/>}></Route>
                    <Route path="/HomeView" element={<HomeView/>}></Route>
                    <Route path="/BookView/:name" element={<BookView/>}></Route>
                    <Route path="/CartView" element={<CartView/>}></Route>
                    <Route path={"/OrderView"} element={<OrderView/>}></Route>
                </Routes>
            </BrowserRouter>
        );
    }
}
