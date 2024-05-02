/*1-3-8:*/
select vendor_name,vendor_contact_last_name,vendor_contact_first_name from vendors;
select vendor_name,vendor_contact_last_name,vendor_contact_first_name 
from vendors 
order by vendor_contact_last_name,vendor_contact_first_name asc;

/*1-3-9*/
select concat(vendor_contact_last_name,', ',vendor_contact_first_name) as full_name
from Vendors
where vendor_contact_last_name regexp '^[ABCE]'
order by vendor_contact_last_name, vendor_contact_first_name asc;

/*1-3-10*/
select invoice_due_date as "Due Date",
		invoice_total as "Invoice Total",
        invoice_total*0.1 as "10%",
        invoice_total*1.1 as "plus_10%"
from invoices
where invoice_total between 500 and 1000
order by invoice_due_date desc;

/*1-3-11*/
select invoice_number, invoice_total, 
payment_total+credit_total as payment_credit_total, 
invoice_total-payment_total-payment_total as balance_due
from invoices
where invoice_total-payment_total-payment_total>50
order by balance_due desc
limit 5;

/*1-3-12*/
select invoice_number, invoice_date,
invoice_total-payment_total-credit_total as balance_due,
payment_date
from invoices
where payment_date is null;

/*1-3-13:*/
select current_date(), date_format(current_date(),"%m-%d-%Y") as "current_date";

/*1-3-14*/
select 50000 as starting_principal,
0.065*50000 as interest,
0.065*50000+50000 as principal_plus_interest;


