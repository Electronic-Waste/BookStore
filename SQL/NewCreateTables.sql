create table book (
    book_id  int AUTO_INCREMENT,
    bookname char(30) not null,
    author char(20),
    type char(10),
    price double,
    description char(255),
    inventory numeric(5,0),
    image char(50),
    primary key (book_id)
);

create table user (
    user_id int AUTO_INCREMENT,
    username char(20) not null,
    password char(20),
    role int,
    primary key (user_id)
);

create table orders (
    order_id int AUTO_INCREMENT,
    user_id int,
    price double,
    primary key (order_id),
    foreign key (user_id) references user(user_id)
);

create table cart (
    cart_id int AUTO_INCREMENT,
    user_id int,
    book_id int,
    price int,
    num int,
    primary key (cart_id),
    foreign key (user_id) references user(user_id),
    foreign key (book_id) references book(book_id)
);

create table orderItem (
    orderitem_id int AUTO_INCREMENT,
    order_id int,
    book_id int,
    price double,
    num int,
    primary key (orderitem_id),
    foreign key (order_id) references orders(order_id),
    foreign key (book_id) references book(book_id)
);
