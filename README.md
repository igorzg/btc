# btc
Spring boot 

# Requirements

* [Docker](https://docs.docker.com/install/)
* [Docker-Compose](https://docs.docker.com/compose/install/)

# Running
```
docker-compose up
```
Command will compile java code and run in docker, it can take a while.

You can manually build & run tests in btc-spring-app folder but you will need jdk & gradle.

# View
* [Spring BootApp - port - 5010](http://localhost:5010/)
* [Python App - port - 5000](http://localhost:5000/)


## REST API
GET 
* http://localhost:5010/accounts
* http://localhost:5010/accounts/1
* http://localhost:5010/orders
* http://localhost:5010/orders/1

POST
* http://localhost:5010/accounts
```json
{
  "name": "acc_one",
  "usd_balance": 10.0,
  "btc_balance": 0.0
}
```
* http://localhost:5010/orders
```json
{
  "details": "order one",
  "account_id": 1,
  "price_limit": 10.0,
  "processed": false
}
```

