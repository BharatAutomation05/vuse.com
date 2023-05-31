Feature: add to cart from product listing page

@Reg1
Scenario: To verify items are added to PLP
When user login application "retip41527@glumark.com" and "Asdfg@12345"
And delete items in cart
And Search product 
And Add to cart
Then validate item added successfully

@Reg1
Scenario: To verify items are added to PDP
When user login application "retip41527@glumark.com" and "Asdfg@12345"
And delete items in cart
And Search product 
And clicking on product image
And Add to cart
Then validate item added successfully