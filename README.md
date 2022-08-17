# bankTransactionsAPI
A bank transactions API built using Java, Sprng boot, PostgreSQL.

## Controller

Contains controllers for customer registration, account registration, airtime purchase and funds transfer.

|    Method     |            Path            |               Description             |   
| ------------- |        ------------        |             --------------            |
|      Post     |        /customers          |           Create customer             |
|      Post     |   /accounts/{customerId}   |       Create customer account         |
|      Post     |   /airtime/{customerId}    |          Airtime purchase             |
|      Post     |   /transfer/{customerId}   |   Transfer with different discounts   |

Postgres was used for data collection.
