import React, {Component} from "react";
import "../css/cart.css"

export class CartItem extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            btnName: this.props.btnName
        }
    }

    render() {
        return(
            <div className="cartItem">
                {/*<div className="cartItemImage">*/}
                {/*    <img src={data[0][6]}/>*/}
                {/*</div>*/}
                {/*<div className="cartItemName">*/}
                {/*    <h3>{data[0][0]}</h3>*/}
                {/*</div>*/}
                {/*<div className="cartItemPrice">*/}
                {/*    <h2>￥{data[0][3]}</h2>*/}
                {/*</div>*/}
                {/*<div className="cartItemBtn">*/}
                {/*    <input type="button" value={this.state.btnName}/>*/}
                {/*</div>*/}
            </div>
            )
    }
}