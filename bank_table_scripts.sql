DROP DATABASE IF EXISTS `bank-user`;
CREATE DATABASE IF NOT EXISTS `bank-user`;
USE `bank-user`;

drop table if exists customer;
drop table if exists employee;
drop table if exists department;

create table customer(
	id integer primary key AUTO_INCREMENT,
	name varchar(50) not null, 
	aadhar_id varchar(50) not null,
	password varchar(50) not null, 
	date_of_birth date not null, 
	gender char not null, 
	address varchar(50) not null, 
	phone_no varchar(10) not null, 
	email_id varchar(50) not null, 
	joining_date date not null, 
	account_id integer
);

create table employee(
	id integer primary key AUTO_INCREMENT,
	name varchar(50) not null, 
	password varchar(50) not null, 
	date_of_birth date not null, 
	gender char not null, 
	address varchar(50) not null, 
	phone_no varchar(10) not null, 
	email_id varchar(50) not null, 
	joining_date date not null, 
	account_id integer ,
	department_id integer 
);

create table department(
	id integer primary key AUTO_INCREMENT,
	name varchar(50) not null,
	department_type varchar(50) not null
);

insert into department values (101, 'Data entry', 'ACCOUNTS');
insert into department values (102, 'Loan Manager', 'LOAN');
insert into department values (103, 'Check Validator', 'GRIEVANCE');
insert into department values (104, 'Inspection', 'SECURITY');
insert into department values (105, 'Insurance Agent', 'INSURANCE');

insert into employee values (101, 'Rahul', 'rahul@123', '1994-04-14', 'M', 'Raipur', '9874563210', 'rahul@gmail.com', '2014-12-07', null, 102);
insert into employee values (102, 'Priya', 'priya@123', '1996-02-22', 'F', 'Bhopal', '9896563267', 'priya@gmail.com', '2012-07-02', null, 103);
insert into employee values (103, 'Kim', 'kim@123', '1990-08-11', 'F', 'Punjab', '9874523690', 'kim@gmail.com', '2010-02-14', 104, 104);
insert into employee values (104, 'Shiv', 'shiv@123', '1995-03-10', 'M', 'Pune', '8745639911', 'shiv@gmail.com', '2019-02-17', null, 101);

insert into customer values (101, 'Aniket', '852063147852', 'aniket@123', '1992-06-14', 'M', 'Kolkata', '7785236520', 'aniket@gmail.com', '2012-06-12', 101);
insert into customer values (102, 'Kajal', '852063147814', 'kajal@123', '1997-09-04', 'F', 'Jabalpur', '7789654123', 'kajal@gmail.com', '2020-03-15', 102);
insert into customer values (103, 'Ashish', '852063147878', 'ashish@123', '1999-11-03', 'M', 'Lucknow', '7785201479', 'ashish@gmail.com', '2019-12-01', 103);
insert into customer values (104, 'Kim', '987456321452', 'kim@123', '1990-08-11', 'F', 'Punjab', '9874523690', 'kim@gmail.com', '2010-02-14', 104);

DROP DATABASE IF EXISTS `bank-account`;
CREATE DATABASE IF NOT EXISTS `bank-account`;
USE `bank-account`;

drop table if exists account;

create table account(
	id integer primary key AUTO_INCREMENT,
	account_number varchar(50) not null, 
	branch_location varchar(50) not null,
	account_type varchar(50) not null,
	interest_rate decimal(4,2) not null,
	opening_date varchar(50) not null,
	is_active boolean not null,
	card varchar(50) not null,
	balance DECIMAL(8,2) not null
);

insert into account values (101, 'BACC101', 'Pune', 'SAVINGS', 6.5, '2017-06-12', true, '9632456312874521', 12500);
insert into account values (102, 'BACC102', 'Delhi', 'CURRENT', 4.7, '2015-10-21', true, '8520963074107810', 21000);
insert into account values (103, 'BACC103', 'Bhopal', 'SAVINGS', 5.2, '2016-02-06', false, '4785985632140365', 52000);
insert into account values (104, 'BACC104', 'Jaipur', 'CURRENT', 8, '2010-01-10', true, '7896412532561236', 4123);


DROP DATABASE IF EXISTS `bank-transactions`;
CREATE DATABASE IF NOT EXISTS `bank-transactions`;
USE `bank-transactions`;

drop table if exists transaction_table;

create table transaction_table(
	id integer primary key AUTO_INCREMENT,
	source varchar(50) not null, 
	destination varchar(50) not null,
	transaction_type varchar(50) not null, 
	time_stamp timestamp not null, 
	status varchar(50) not null
);

insert into transaction_table values (101, 'BACC101', 'BACC104', 'INSTANT TRANSFER', '2018-01-19 03:14:07', 'COMPLETED');
insert into transaction_table values (102, 'BACC104', 'BACC102', 'DRAFT', '2018-06-09 07:13:19', 'COMPLETED');
insert into transaction_table values (103, 'BACC103', 'BACC102', 'INSTALLMENT', '2018-12-05 05:42:07', 'FAILED');
