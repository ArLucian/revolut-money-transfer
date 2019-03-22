# Revolut Money Transfer


A RESTful API that allows the transfer of amounts between accounts of any currency.
 
## How to start

1. mvn clean install

This will install dependencies, run all tests and build the application.

2. mvn spring-boot:run

The application will start. 
Port is configurable in application.properties under server.port property(default 8500).

## App Features

* Account Creation (any currency).
* Transfer money between account in any currency.
* Exchange rate service (Java Money and Currency API).
* RESTful return status codes
* H2 in memeory database to store of accounts and transactions.
* Spring (Dependency Injection and REST API).
* Unit Tests (Using Junit and Mockito).
* Builder Design pattern for Entities and Models.

*For the API HATEOAS was not used since nowadays the client tools supporting hateoas are very limited. But soon it
could be an option.

## Requires
* Java 8
* Maven

## Documentation

### Accounts

#### Post method: Creates an account and returns the created resource

Request example: 

    POST /accounts
    {
        "id": 3,
        "balance": 50000,
        "currency": "USD"
    }

Response example:

	{
	    "text": "Account was created.",
	    "id": 3,
	    "balance": 50000,
	    "currency": "USD"
	}

#### Get all method: Retrieve all accounts from the database

    GET /accounts

Response example:

    {
        "id": 1,
        "balance": 50000,
        "currency": "USD"
    },
    {
        "id": 2,
        "balance": 15000,
        "currency": "EUR"
    }

#### Get by identifier: Get account by id number

    GET /accounts/1

Example response:

    {
        "id": 1,
        "balance": 50000,
        "currency": "USD"
    }

### Transactions

#### Post method: Create a transaction between the given account with validations.

Request example:

    {
    	"id":1,
        "fromAccount": 1,
        "toAccount": 2,
        "amount": 10000,
        "currency": "EUR"
    }

Example response:

    {
    "responseText": "Transaction successful !",
    "exchangedAmount": 10000,
    "currency": "EUR",
    "fromAccount": 1,
    "toAccount": 2
	}

#### GET method: Get all transactions

Example response:

    {
     "id": 1,
     "fromAccount": 1,
     "toAccount": 2,
     "amount": 10000,
     "currency": "EUR",
     "status": "FINISHED"
    }
    
#### GET specific method: Get transaction by id

Example response:

    {
     "id": 1,
     "fromAccount": 1,
     "toAccount": 2,
     "amount": 10000,
     "currency": "EUR",
     "status": "FINISHED"
    }    
