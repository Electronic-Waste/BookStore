import React from "react";

import "../css/home.css"
import searchIcon from "../assets/search.svg"
import Search from "antd/es/input/Search";
import {Switch} from "antd";

export class SearchBox extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            isFullText: false
        }
        this.switchState = this.switchState.bind(this);
    }

    getPlaceHolder = () =>
        this.state.isFullText ? "Input keywords of the book's description" : "Input the book name";

    switchState(e) {
        this.setState({
            isFullText: !this.state.isFullText
        })
    }

    render() {
        return (
            <div className="search">
                <div style={{marginLeft: "500px"}}>
                    <Switch
                        id = "search-switch"
                        checkedChildren="Book-Name-Search"
                        unCheckedChildren="Book-Label-Search"
                        onChange={this.switchState}
                        defaultChecked/>
                </div>
                <div style={{marginTop: "20px"}}>
                    <Search
                        id = "search-bar"
                        placeholder={this.getPlaceHolder()}
                        allowClear
                        enterButton="Search"
                        size="large"
                        onSearch={this.props.search}
                    />
                </div>
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
