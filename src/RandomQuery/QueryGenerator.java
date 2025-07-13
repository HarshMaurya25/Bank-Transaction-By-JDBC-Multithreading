package RandomQuery;

import SQLQuery.TransactionLogger;

import java.sql.*;
import java.util.concurrent.locks.ReentrantLock;

public class QueryGenerator {
    public static final String Query = "UPDATE Account SET balance = balance + ? WHERE ACCOUNT_NUMBER = ?";
    private static final ReentrantLock LOCK = new ReentrantLock(true);
    private static PreparedStatement preparedStatement;
    private static Connection connection;
    private static TransactionLogger logQuery;


    public QueryGenerator(Connection connection , TransactionLogger logQuery){
        try {
            QueryGenerator.connection= connection;
            QueryGenerator.logQuery = logQuery;
            preparedStatement = connection.prepareStatement(Query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void QueryGenrator(){
        try {
            RandomAccount acc = new RandomAccount();
            int num = acc.AccountGenerator();

            BalanceGenerator balanceGenerator = new BalanceGenerator();
            Statement statement = QueryGenerator.connection.createStatement();

            LOCK.lock();
            Thread.sleep(1000);
            int account_num = num;
            int balance = balanceGenerator.AmountGenerator(statement  , num);
            preparedStatement.setDouble(1,balance);
            preparedStatement.setInt(2,num);
            int result = preparedStatement.executeUpdate();

            QueryGenerator.connection.commit();
            LOCK.unlock();
            logQuery.EnterLog(account_num, balance);


        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
