import Create.AccountOpen;
import Create.PrivateData;
import MultiThreading.MultiThreeading;
import RandomQuery.QueryGenerator;
import SQLQuery.TransactionLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/bankstransaction";
    private static final String name = "root";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url ,name , PrivateData.GetPassWord());
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();

            AccountOpen accountOpen = new AccountOpen(connection);
            PrivateData privateData = new PrivateData(connection);

            TransactionLogger logQuery = new TransactionLogger(url , name, PrivateData.GetPassWord());

            QueryGenerator queryGenerator = new QueryGenerator(connection , logQuery);
            System.out.println(PrivateData.getAccount_detail().toString());

            MultiThreeading.Threads();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
