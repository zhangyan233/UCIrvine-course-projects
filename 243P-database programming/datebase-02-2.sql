/*1-4-1*/
select * 
from vendors
inner join invoices on vendors.vendor_id=invoices.vendor_id;

/*1-4-2*/
select v.vendor_name as vendor_name, i.invoice_number as invoice_number,
       i.invoice_date as invoice_date, i.invoice_total-i.payment_total-i.credit_total as balance_due
from vendors v
left join invoices i on v.vendor_id=i.vendor_id
where i.invoice_total-i.payment_total-i.credit_total!=0
order by v.vendor_name asc;

/*1-4-3*/
select vendors.vendor_name as vendor_name, vendors.default_account_number as default_account,
	general_ledger_accounts.account_description as description
from vendors
left join general_ledger_accounts on vendors.default_account_number=general_ledger_accounts.account_number
order by general_ledger_accounts.account_description,vendors.vendor_name;

/*1-4-4*/
select v.vendor_name as vendor_name, i.invoice_date as invoice_date,
       i.invoice_number as invoice_number, ili.invoice_sequence as li_sequence,
       ili.line_item_amount as li_amount
from invoices i
left join vendors v on i.vendor_id=v.vendor_id
left join invoice_line_items ili on i.invoice_id=ili.invoice_id
order by v.vendor_name, i.invoice_date,i.invoice_number,ili.invoice_sequence;

/*1-4-5*/
select v1.vendor_id as vendor_id, v1.vendor_name as vendor_name,
	   concat(v1.vendor_contact_first_name,' ',v1.vendor_contact_last_name)
from vendors v1
join vendors v2 on v1.vendor_id!=v2.vendor_id and v1.vendor_contact_last_name=v2.vendor_contact_last_name
order by v1.vendor_contact_last_name;

/*1-4-6*/
select g.account_number as account_number, g.account_description as account_description, i.invoice_id as invoice_id
from general_ledger_accounts g
left join invoice_line_items i on g.account_number=i.account_number
where i.invoice_id is null
order by g.account_number;

select g.account_number as account_number, g.account_description as account_description
from general_ledger_accounts g
left join invoice_line_items i on g.account_number=i.account_number
where i.invoice_id is null
order by g.account_number;

/*1-4-7*/
select vendor_name, "CA" as vendor_state
from vendors
where vendor_state="CA"
union
select vendor_name, "Outside CA" as vendor_state
from vendors
where vendor_state!="CA"
order by vendor_name;

