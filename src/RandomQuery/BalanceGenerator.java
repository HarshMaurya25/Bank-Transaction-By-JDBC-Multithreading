package RandomQuery;

import Create.PrivateData;

import java.sql.*;
import java.util.Random;

public class BalanceGenerator {
    private static final Random random = new Random();

    public int AmountGenerator(Statement statement, int account_number){
        String Query = String.format("Select balance from account where account_number = %o" , account_number);
        try {

            ResultSet resultSet = statement.executeQuery(Query);
            if(resultSet.next()){
                int balance = (int) resultSet.getDouble("Balance");
                return random.nextInt((int) (-balance + 1), balance);
            }else{
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
