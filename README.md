# MM Finances API

## About

MM Finances is a web app that helps you track how you spend your money.

The idea for this app came from a friend of mine who used a difficult to understand spreadsheet to work out how he spends his income. I tried to come up with a solution to make this process easier and more visually pleasing, and MM Finances is what I ended up with.

The MM Finances API is a RESTful API created to be consumed by <a href="https://github.com/bobel95/MM-finances-react">MM Finances React</a>

[comment]: <> (<p align="center">)

[comment]: <> (    <img src="https://i.imgur.com/dxuZoQU.png" width="600"/>)

[comment]: <> (</p>)

## Project Status

Currently working on the project in 1 week sprints.

Completed sprints: `1`

## Features

* RESTful API
* JWT Authentication
* Optional query params for filtering results implemented with JPA's Specification API

## Technologies used

* <a href="https://www.java.com/en/">Java</a>
* <a href="https://github.com/apache/maven">Maven</a>
* <a href="https://github.com/spring-projects/spring-boot">Spring Boot</a>
* <a href="https://www.postman.com/">Postman</a>
* <a href="https://swagger.io/">Swagger</a>
* <a href="http://hibernate.org/">Hibernate</a>
* <a href="https://www.postgresql.org/">PostgreSQL</a>

## Usage:

### Resources

### `payment` (endpoint: `/api/payment/`)

Model
```json
{
  "appUser": {...},
  "date": "yyyy-MM-dd",
  "id": integer,
  "paymentCategory": enum,
  "money": {
    "amount": string,
    "currency": string
  }
}
```

The payment model uses `org.javamoney.moneta.Money` for the `money` data field. Check out <a href="https://github.com/zalando/jackson-datatype-money">Jackson datatype money</a> to see more about the serialization and deserialization of this data type.

Available endpoints and methods:
  * `GET` - `/api/payment`
      * optional query param: `category: paymentCategory enum`
      * optional query param: `date: String (format yyyy-MM-dd)`
  * `PUT` - `/api/payment`
  * `DELETE` - `api/payment`
  * `POST` - `/api/payment/{appUserId}`

### `appUser` (endpoint: `/api/user/`)

Model
```json
{
  "id": long,
  "email": string,
  "firstName": string,
  "lastName": string,
  "paymentList": [...]
}
```
Available endpoints and methods:
* `GET` - `/api/user/{appUserId}`
* `PUT` - `/api/user`
* `DELETE` - `/api/user/{appUserId}`
* `POST` - `/api/user`

### `authentication` (endpoint: `/authenticate`)
* Method: `POST`
* Request body:
```json
{
  "password": string,
  "username": string
}
```
* Response:
```json
{
  "jwt": string,
  "userId": long,
  "username": string
}
```
