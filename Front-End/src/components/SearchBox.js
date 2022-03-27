import React from "react";

import "../css/home.css"
import searchIcon from "../assets/search.svg"

export class SearchBox extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="search">
                <input
                    className="search_box" type="search"
                    placeholder="Find some books there"
                    onChange={this.props.search}
                />
                <img className="search_btn" src={searchIcon}/>
            </div>
        );
    }
}

export function ClassifyItem(itemGroup) {
    return (
        <div className="classifyItem">
            <button>{itemGroup}</button>
        </div>
    );
}

export class ClassifyBox extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="classify">
                {ClassifyItem("#Coding")}
                {ClassifyItem("#Magic Novel")}
                {ClassifyItem("#World Master Pieces")}
                {ClassifyItem("More...")}
            </div>
        );
    }
}
