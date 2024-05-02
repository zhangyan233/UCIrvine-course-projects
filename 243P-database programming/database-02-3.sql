/*1-5-1*/
insert terms(terms_id,terms_description,terms_due_days) value (6,"Net due 120 days",120);

/*1-5-2*/
update terms set terms_description="Net due 125 days", terms_due_days=125 where terms_id=6;

/*1-5-3*/
delete from terms where terms_id=6;

/*1-5-4*/
insert invoices value (default,32,"AX-014-027",'2018-01-08',434.58,0.00,0.00,2,'2018-08-31',NULL);

/*1-5-5*/
insert invoice_line_items(invoice_id,invoice_sequence,account_number,line_item_amount,line_item_description) values
(LAST_INSERT_ID(),1,160,180.23,"Hard drive"),
(LAST_INSERT_ID(),2,527,254.35,"Exchange Server update");

/*1-5-6*/
UPDATE Invoices 
SET credit_total = invoice_total * 0.10, 
    payment_total = invoice_total - (invoice_total * 0.10)
WHERE invoice_id = LAST_INSERT_ID();

/*1-5-7*/
update vendors set default_account_number=403 where vendor_id=44;

/*1-5-8*/
update invoices set terms_id=2 where vendor_id in 
(select vendor_id from vendors where default_terms_id=2);

/*1-5-9*/
DELETE FROM Invoice_line_Items WHERE invoice_id = LAST_INSERT_ID();
DELETE FROM Invoices WHERE invoice_id = LAST_INSERT_ID();




