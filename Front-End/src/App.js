import './App.css';
import React from "react";

import {LoginView} from "./view/LoginView";
import {HomeView} from "./view/HomeView";
import {BookView} from "./view/BookView";
import {BasicRoute} from "./Router"

class App extends React.Component {

    render() {
        return (
            <BasicRoute/>
        );
    }
}

export default App;
