import React from "react";
import pic1 from "../assets/carousel/book1.jpg"
import pic2 from "../assets/carousel/book2.jpg"
import pic3 from "../assets/carousel/book3.jpg"
import pic4 from "../assets/carousel/book4.jpg"

import "../css/home.css"

export class CarouselBar extends React.Component {
    render() {
        return (
            <div className="carouselBar">
                <ul style={{display: "flex"}}>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>
        );
    }
}

export class BookCarousel extends React.Component {

    render() {
        return (
            <div className="bookCarousel">
                <img src={pic3}/>
                <CarouselBar/>
            </div>
        );
    }
}
