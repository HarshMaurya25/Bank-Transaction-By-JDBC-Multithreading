package SQLQuery;

import RandomQuery.NumberGenrator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionLogger {
    private static PreparedStatement preparedStatement;
    private static Connection connection;
    private static final String Query = "INSERT INTO log(atm_no , account_number ,amount , type) values (? , ? , ? , ?)";
    private static final ReentrantLock lock = new ReentrantLock();

    public TransactionLogger(String url , String name , String password) {
        try{
            TransactionLogger.connection = DriverManager.getConnection(url , name , password);
            TransactionLogger.preparedStatement = connection.prepareStatement(TransactionLogger.Query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void EnterLog( int account_number , int amount){
        try {
            lock.lock();
            int atm_no = NumberGenrator.atmGenerator();

            preparedStatement.setInt(1,atm_no);
            preparedStatement.setInt(2,account_number);
            if(amount < 0 ){
                preparedStatement.setDouble(3, -amount);
                preparedStatement.setString(4,"Withdraw");
            }else{
                preparedStatement.setDouble(3, amount);
                preparedStatement.setString(4,"Deposite");
            }

            int result = preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
