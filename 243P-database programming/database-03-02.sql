/*1-11-1*/
create index vendors_zip_code_ix on vendors(vendor_zip_code);

/*1-11-2*/
use ex; /*choose database*/
/*drop tables if exist*/
drop table if exists members_committes;
drop table if exists members;
drop table if exists committees;

create table members 
(
  member_id              int           primary key   auto_increment, 
  member_firstname    varchar(50)        not null, 
  member_lastname     varchar(50)        not null, 
  address             varchar(50)        not null, 
  city                varchar(25)        not null, 
  state               varchar(10)        not null, 
  phone               varchar(20)        not null
);

create table committees 
(
  committee_id      int           primary key   auto_increment,  
  committee_name    varchar(50)     not null
);

create table members_committees
(
member_id         int    not null, 
committee_id      int    not null,
constraint members_committee_fk_members foreign key(member_id) references members(member_id),
constraint members_committee_fk_committees foreign key(committee_id) references committees(committee_id)
);

/*1-11-3*/
insert members values (default,'Tina','Han','palo verde','Irvine','California','1111111111'),
(default,'Linda','Zhang','palo verde','Irvine','California','2222222222');
insert committees values (default,'go hike'),(default,'let swim');
insert members_committees values(1,2),(2,1),(2,2);

select c.committee_name, m.member_lastname, m.member_firstname
from members_committees mc
join members m on m.member_id=mc.member_id
join committees c on c.committee_id=mc.committee_id
order by c.committee_name, m.member_lastname, m.member_firstname;

/*1-11-4*/
alter table members add  annual_due decimal(5,2) default 52.50;
alter table members add payment_date date;

/*1-11-5*/
alter table committees modify committee_name varchar(50) not null unique;
insert committees values (default,'let swim');