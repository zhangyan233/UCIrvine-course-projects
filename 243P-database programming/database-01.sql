/*2-7*/
select vendor_name from vendors;

/*2-8*/
select vendor_nam from vendors;

/*2-9*/
select count(*) as number_of_invoices, sum(invoice_total) as grand_invoice_total
from invoices;