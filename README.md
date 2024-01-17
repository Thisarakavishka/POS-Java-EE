# POS-Java-EE (POS) API

## Overview
This repository contains a Java EE API for a Point of Sale (POS) system. The API provides endpoints for managing customers, items, and orders.

## Technologies Used
- **Java EE**
- **JNDI**
- **Servlets**
- **ObjectMapper (Jackson library)**

## API Endpoints

### Customer Management
#### Get All Customers
- **URL:** http://localhost:8080/pos/customer
- **Method:** GET
- **Description:** Retrieve a list of all customers.

#### Get Customer by ID
- **URL:** http://localhost:8080/pos/customer?customerId={id}
- **Method:** GET
- **Description:** Retrieve customer details by ID.

#### Add Customer
- **URL:** http://localhost:8080/pos/customer
- **Method:** POST
- **Description:** Add a new customer.
- **Request Body:** JSON representing the customer.

#### Update Customer
- **URL:** http://localhost:8080/pos/customer
- **Method:** PUT
- **Description:** Update an existing customer.
- **Request Body:** JSON representing the updated customer.

#### Delete Customer
- **URL:** http://localhost:8080/pos/customer?customerId={id}
- **Method:** DELETE
- **Description:** Delete a customer by ID.

### Item Management
#### Get All Items
- **URL:** http://localhost:8080/pos/item
- **Method:** GET
- **Description:** Retrieve a list of all items.

#### Get Item by Code
- **URL:** http://localhost:8080/pos/item?itemCode={code}
- **Method:** GET
- **Description:** Retrieve item details by code.

#### Add Item
- **URL:** http://localhost:8080/pos/item
- **Method:** POST
- **Description:** Add a new item.
- **Request Body:** JSON representing the item.

#### Update Item
- **URL:** http://localhost:8080/pos/item
- **Method:** PUT
- **Description:** Update an existing item.
- **Request Body:** JSON representing the updated item.

#### Delete Item
- **URL:** http://localhost:8080/pos/item?itemCode={code}
- **Method:** DELETE
- **Description:** Delete an item by code.

### Order Management
#### Get All Orders
- **URL:** http://localhost:8080/pos/order
- **Method:** GET
- **Description:** Retrieve a list of all orders.

#### Get Order by ID
- **URL:** http://localhost:8080/pos/order?orderId={id}
- **Method:** GET
- **Description:** Retrieve order details by ID.

#### Add Order
- **URL:** http://localhost:8080/pos/order
- **Method:** POST
- **Description:** Add a new order.
- **Request Body:** JSON representing the order.

#### Update Order
- **URL:** http://localhost:8080/pos/order
- **Method:** PUT
- **Description:** Update an existing order.
- **Request Body:** JSON representing the updated order.

#### Delete Order
- **URL:** http://localhost:8080/pos/order?orderId={id}
- **Method:** DELETE
- **Description:** Delete an order by ID.

## Using Postman
You can use Postman to interact with the API. Follow these steps:
1. Open Postman.
2. Choose the appropriate HTTP method (GET, POST, PUT, DELETE).
3. Enter the corresponding API endpoint URL.
4. Set the request body to JSON format when required.
5. Send the request.

## Sample JSON Payloads
### Customer
```json
{
  "customerId": "C0045",
  "customerName": "John Doe",
  "address": "123 Main St",
  "salary": "340000"
}
```
### Item
```json
{
  "itemCode": "I0342",
  "itemName": "Gadget",
  "qty": 45,
  "price": 259
}

```