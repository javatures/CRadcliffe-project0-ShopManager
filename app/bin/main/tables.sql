create table customers (
    id serial primary Key,
    fName text not null,
    lName text not null,
    phone_number text not null
);
create table cars (
    owner_id integer not null references customers (id),
    year text not null,
    make text not null,
    model text not null,
    vin text not null primary key,
    license_plate_state text not null,
    license_plate_number text not null
);
create table jobs (
    customer_id integer references customers (id),
    car_id text  REFERENCES cars(vin) ON UPDATE CASCADE,
    job_number serial primary key,
    invoiced boolean not null,
    paid boolean not null,
    taxable boolean not null
    
);
create table items (
    id serial primary key,
    job_id integer not null references jobs (job_number),
    item_number text not null,
    item_description text,
    taxable boolean not null,
    unit_cost numeric (5, 2) not null check (unit_cost > 0),
    quantity NUMERIC(3 , 2) not null check (quantity > 0)
);
