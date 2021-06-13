# MonoLithic Backend of Bank Application
It's a monolithic structure of a Bank application containing 3 main features or components: `Accounts`, `Transactions` and `User`. 
`Users` can be either Customers or Employees.

---
## Account provides following behaviours:
 1. can create new Account for new users
 2. fetch account by id
 3. fetch all accounts by employees
 4. update an existing account by employee
 5. can delete an account
 6. can perform a transaction between users
---

## Transaction provides following behaviours:
 1. can get all transactions by source account id
 2. can get all transactions by destination account id
 3. can perform transactions and update account balance

---

## User provides following behaviours:
 1. can get list of all customers
 2. get customer by id
 3. can create new customer with new account
 4. can update customer details like email, phone no, address or active status
 5. can delete customer by employee 
