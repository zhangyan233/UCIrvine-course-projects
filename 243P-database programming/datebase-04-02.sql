/*2-7-1*/
select distinct vendor_name
from vendors
where vendors.vendor_id in
(
	select vendor_id from invoices where vendor_id=vendors.vendor_id
)
order by vendor_name;

/*2-7-2*/
select invoice_number, invoice_total 
from invoices
where payment_total>
(
	select avg(payment_total) from invoices where payment_total>0
)
order by invoice_total desc;

/*2-7-3*/
select * from general_ledger_accounts g
where not exists
(
	select * from invoice_line_items i where g.account_number=i.account_number
)
order by g.account_description desc;

/*2-7-4*/
select v.vendor_name, i.invoice_id, ili.invoice_sequence, ili.line_item_amount
from vendors v
join invoices i on v.vendor_id=i.vendor_id
join invoice_line_items ili on i.invoice_id=ili.invoice_id
where i.invoice_id in
(
	select distinct invoice_id from invoice_line_items
    where invoice_sequence>1
)
order by v.vendor_name, i.invoice_id,ili.invoice_sequence;

/*2-7-5*/
select vendor_id,max(invoice_total-payment_total-credit_total) as largest_unpaid
from invoices
group by vendor_id
having largest_unpaid>0;


select sum(largest_unpaid) as largest_unpaid_total
from 
(
	select vendor_id,max(invoice_total) as largest_unpaid
	from invoices
    where invoice_total-payment_total-credit_total>0
	group by vendor_id
) t;

/*2-7-6*/
select vendor_name, vendor_city,vendor_state
from vendors
where concat(vendor_city,vendor_state) in
(
	select concat(vendor_city,vendor_state) as con from vendors
    group by vendor_city,vendor_state
    having count(*)=1
)
order by vendor_state,vendor_city;

/*2-7-7*/
select v.vendor_name,i.invoice_number,i.invoice_date,i.invoice_total
from invoices i join vendors v on i.vendor_id=v.vendor_id
where i.invoice_date in
(
	select min(invoice_date) from invoices 
    where vendor_id=v.vendor_id
)
order by vendor_name;

