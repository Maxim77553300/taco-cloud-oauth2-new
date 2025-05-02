CREATE TABLE IF NOT EXISTS Taco_Order (
    id varchar(50) not null,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Taco (
    id varchar(50) not null,
    name varchar(50) not null,
    taco_order varchar(50) not null,
    taco_order_key bigint not null,
    created_at timestamp not null,
    CONSTRAINT FK_TACO_ORDER FOREIGN KEY (taco_order) REFERENCES Taco_Order(id)
);

CREATE TABLE IF NOT EXISTS Ingredient_Ref (
    ingredient varchar(4) not null,
    taco varchar(50) not null,
    taco_key bigint not null
);

CREATE TABLE IF NOT EXISTS Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null,
    CONSTRAINT FK_INGREDIENT FOREIGN KEY (ingredient) references Ingredient(id)
);

-- ALTER TABLE Taco ADD CONSTRAINT FK_TACO_ORDER FOREIGN KEY (taco_order) REFERENCES Taco_Order(id);
-- ALTER TABLE Ingredient_Ref ADD CONSTRAINT FK_INGREDIENT FOREIGN KEY (ingredient) references Ingredient(id);