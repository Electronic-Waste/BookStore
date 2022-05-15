create table Book (
    BookID  char(5),
    BookName char(30) not null,
    Author char(20),
    Type char(10),
    Price double,
    Description char(255),
    Inventory numeric(5,0),
    Image char(50),
    primary key (BookID)
);

create table User (
    UserID varchar(6),
    UserName char(20) not null,
    Password char(20),
    Role int,
    primary key (UserID)
);

create table Orders (
    OrderID int AUTO_INCREMENT,
    UserID varchar(6),
    Price double,
    primary key (OrderID),
    foreign key (UserID) references User(UserID)
);

create table Cart (
    UserID varchar(6),
    BookID char(5),
    foreign key (UserID) references User(UserID),
    foreign key (BookID) references Book(BookID)
);

create table OrderItem (
    OrderID int,
    BookID char(5),
    Price double,
    foreign key (OrderID) references Orders(OrderID),
    foreign key (BookID) references Book(BookID)
);
