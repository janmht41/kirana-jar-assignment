# Kirana Register Service Documentation

This documentation provides details on the API endpoints and functionalities of the Kirana Service, a backend service designed to empower Kirana stores in managing their transaction registers.
- **Note: All amounts are stored in INR by default**
## 1. Record Transaction

- **Endpoint:** `POST /record`
- **Description:** Records a new transaction in the Kirana store.
- **Sample Request Body:**
  ```json
  {
    "transactionType": "CREDIT",
    "amount": 100.0,
    "currency": "INR",
    "transactionDesc": "Purchase of goods",
    "paymentMode": "CASH",
    "creationUser": "JohnDoe"
  }
 - **Sample Response Body:**
   ```json
    {
        "Transaction ID": "3209fde6-b32e-4e9e-83cf-d51336063fa7"
    }
   

## 2. Get Transactions by Date

- **Endpoint:** `GET /transactions`
- **Description:** Retrieves a list of transactions for a given date.
- **Query Parameter:**
- `date` (optional): Date in ISO format (e.g., `2024-01-02`). If not provided, returns transactions for the current date.

- **Example Request:**

```http
GET /transactions?date=2024-01-02
```
- **Example Response:**

```json
[
    {
        "transactionId": "0a5d8dfd-38fa-452c-bae0-8f917e5b5f8d",
        "debitAmount": 0.0,
        "transactionTime": "2024-01-03T00:48:39.345685",
        "creditAmount": 6.66,
        "transactionDetails": "dummy",
        "paymentMode": "UPI",
        "creationUser": "user1"
    },
    {
        "transactionId": "0b473241-6c9f-4204-b679-432780e6d0f7",
        "debitAmount": 667.75,
        "transactionTime": "2024-01-03T14:33:46.365498",
        "creditAmount": 0.0,
        "transactionDetails": "milk",
        "paymentMode": "UPI",
        "creationUser": "user2"
    },
    {
        "transactionId": "0ce2896b-1714-456e-ba80-e1477a50f314",
        "debitAmount": 8.02,
        "transactionTime": "2024-01-03T20:28:02.541667",
        "creditAmount": 0.0,
        "transactionDetails": "GROCERY",
        "paymentMode": "UPI",
        "creationUser": "user3"
    }
]
```
## 3. Get Transaction Summary

- **Endpoint:** `GET /summary`
- **Description:** Retrieves total debit and credit transactions made by kirana store on a given date.
- **Query Parameter:**
- `date` (optional): Date in ISO format (e.g., `2024-01-02`). If not provided, returns the summary for the current date.

- **Example Request:**

```http
GET /summary?date=2024-01-02
```
- **Example Response:**

```json
 {
    "creditAmount": 2289.86,
    "debitAmount": 5345.75,
    "transactionDate": "2024-01-03"
}
```


