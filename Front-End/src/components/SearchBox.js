import React from "react";

import "../css/home.css"
import searchIcon from "../assets/search.svg"
import Search from "antd/es/input/Search";

export class SearchBox extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="search">
                <Search
                    id = "search-bar"
                    placeholder="Input the book name"
                    allowClear
                    enterButton="Search"
                    size="large"
                    onSearch={this.props.search}
                />
            </div>
        );
    }
}

export function ClassifyItem(itemGroup, classify) {
    return (
        <div className="classifyItem">
            <button data-type={itemGroup} onClick={classify}>{itemGroup}</button>
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
                {ClassifyItem("#编程", this.props.classify)}
                {ClassifyItem("#世界名著", this.props.classify)}
                {ClassifyItem("#魔幻小说", this.props.classify)}
                {ClassifyItem("#武侠小说", this.props.classify)}
                {ClassifyItem("#更多", this.props.classify)}
            </div>
        );
    }
}
