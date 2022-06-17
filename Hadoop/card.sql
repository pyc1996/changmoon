use bigdata;
create table card(
	rowkey int auto_increment primary key,
	member_sido varchar(10),
    member_gugun varchar(10),
    enterprise  varchar(10),
    gender  varchar(10),
    age varchar(10),
    
    pay_date int,
    approve_time int,
    
    shop_sido  varchar(10),
    shop_gugun  varchar(10),
    shop_dong  varchar(10),
    category int,
    
    pay_amount int,
    pay_number int,
    member_number int
);


alter table bigdata.card disable keys;
load data local infile '/home/ubuntu/card-data/card-data_201909.csv';

select count(*) from card;
