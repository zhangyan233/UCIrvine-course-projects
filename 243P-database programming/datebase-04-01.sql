/*2-6-1*/
select vendor_id, sum(invoice_total) as sum_invoice_total
from invoices
group by vendor_id;

/*2-6-2*/
select v.vendor_name, sum(i.payment_total) as payment_total_sum
from vendors v
join invoices i on v.vendor_id=i.vendor_id
group by v.vendor_id
order by sum(i.payment_total) desc;

/*2-6-3*/
select v.vendor_name, count(*) as count_invoice, sum(i.invoice_total) as invoice_total_sum
from vendors v join invoices i on v.vendor_id=i.vendor_id
group by v.vendor_id
order by count(*) desc;

/*2-6-4*/
select g.account_description, count(*) as count_items, sum(i.line_item_amount) as item_amount_total 
from general_ledger_accounts g join invoice_line_items i on g.account_number=i.account_number
group by g.account_description
having count_items >1
order by  item_amount_total desc;

/*2-6-5*/
select g.account_description, count(*) as count_items, sum(ili.line_item_amount) as item_amount_total 
from invoice_line_items ili 
join general_ledger_accounts g on g.account_number=ili.account_number
join invoices i on i.invoice_id=ili.invoice_id
where i.invoice_date between '2018-04-01' and '2018-06-30'
group by g.account_description
having count_items >1
order by  item_amount_total desc;

/*2-6-6*/
select ili.account_number,sum(ili.line_item_amount) as line_item_amount_total
from invoice_line_items ili
group by ili.account_number with rollup;

/*2-6-7*/
select v.vendor_name, count(distinct ili.account_number) as account_number_total
from vendors v join invoices i on i.vendor_id=v.vendor_id
join invoice_line_items ili on ili.invoice_id=i.invoice_id
group by v.vendor_id
having account_number_total>1;

/*2-6-8*/
select if(grouping(terms_id)=1,'Grand Total',terms_id) as terms_id,
		if(grouping(vendor_id)=1,'Invoice total date',vendor_id) as vendor_id,
        max(payment_date) as last_payment_date,
        sum(invoice_total-payment_total-credit_total) as balance_due_sum
from invoices 
group by terms_id,vendor_id with rollup;
