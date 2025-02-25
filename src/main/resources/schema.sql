create table if not exists transactions(
    id uuid PRIMARY KEY,
    customer VARCHAR(255) NOT NULL,
    reference VARCHAR(255) ,
    amount NUMERIC(19, 2) NOT NULL,
    payment_method VARCHAR(255) NOT NULL ,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table if not exists savingaccounts(
    id SERIAL PRIMARY KEY,
    account_name VARCHAR(255) NOT NULL ,
    total_money  NUMERIC(19,2) NOT NULL ,
    rate NUMERIC (19,2)  NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
