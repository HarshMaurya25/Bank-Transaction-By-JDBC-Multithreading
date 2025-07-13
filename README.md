# 💳 Multi-threaded ATM Transaction System (JDBC + Java)

This project simulates a *multi-threaded ATM banking system* where multiple users (represented as threads) perform transactions like deposits and withdrawals *safely and concurrently* using *JDBC and ReentrantLock*.

It uses a *MySQL/SQLite database* to store account information, and ensures *thread-safe operations* with proper synchronization and transaction logging.

---

## 🚀 Features

- 🧑 Create and load real user accounts from the database
- 🔐 PIN-based account authentication
- 🔁 Simulated deposit and withdrawal operations using random threads
- 💾 Transaction logging for every update
- 🔒 Thread safety with ReentrantLock(true) to prevent race conditions
- 🧵 50+ concurrent threads simulating ATM usage
- ✅ Supports clean DB integration using JDBC
- 📄 Simple console-based output for demo/testing

---

## 🧠 Concepts Used

- Java Multithreading (ExecutorService, Runnable)
- Thread synchronization (ReentrantLock)
- JDBC (Java Database Connectivity)
- Transaction simulation
- Data access object (DAO) pattern
- Logging with timestamps

---

## 🗃 Database Schema

```sql
CREATE TABLE account (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    pin VARCHAR(4),
    balance BIGINT
);

CREATE TABLE transaction_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    type VARCHAR(10), -- deposit or withdraw
    amount BIGINT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);
