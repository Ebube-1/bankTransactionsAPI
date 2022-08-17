# Bank Transactions API
A bank transactions API built using Java, Sprng boot, PostgreSQL. 
The following percentage discounts when included;

- If the customer is a business user, he gets 27% discount on transactions above 150,000 after three transactions within the same month.
- If the customer is a retail user, he gets 18% discount on transactions above 50,000 after three transactions within the same month.
- If a user has been a customer for over 4 years, he gets a 10% discount for every first three transactions in a month.
- The percentage discount does not apply on airtime recharge

## Controller

Contains controllers for customer registration, account registration, airtime purchase and funds transfer.

|    Method     |            Path            |               Description             |   
| ------------- |        ------------        |             --------------            |
|      Post     |        /customers          |           Create customer             |
|      Post     |   /accounts/{customerId}   |       Create customer account         |
|      Post     |   /airtime/{customerId}    |          Airtime purchase             |
|      Post     |   /transfer/{customerId}   |   Transfer with different discounts   |

Postgres was used for data collection.
