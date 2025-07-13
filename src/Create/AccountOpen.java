package Create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountOpen {
    private static PreparedStatement statement;
    private static PreparedStatement statementAccountNumber;

    private static final String Query = "INSERT INTO ACCOUNT(name , pin) VALUES(? ,?)";
    private static final String QueryAccountNumber = "SELECT ACCOUNT_NUMBER FROM ACCOUNT WHERE NAME = ? AND PIN = ?";

    private static final Scanner sc = new Scanner(System.in);
    private static Connection connection;

    public AccountOpen(Connection connection){
        try {
            AccountOpen.connection = connection;
            AccountOpen.statement = connection.prepareStatement(AccountOpen.Query);
            AccountOpen.statementAccountNumber = connection.prepareStatement(AccountOpen.QueryAccountNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void CreateAccount(){
        try {
            String name;
            int PinCode;

            System.out.printf("Enter The Name : ");
            name = sc.next();
            System.out.printf("Enter The Pin Code : ");
            PinCode = sc.nextInt();

            statement.setString(1 , name);
            statement.setInt(2, PinCode);

            int complete = statement.executeUpdate();

            if(complete > 0){
                AccountOpen.connection.commit();
                if (getAccountNumber(name, PinCode)) {
                    System.out.println("Account Have Being Created");
                } else {
                    System.out.println("Failed!!!");
                }
            }else{
                System.out.println("Failed!!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static boolean getAccountNumber(String name , int pin){
        try {
            statementAccountNumber.setString(1 , name);
            statementAccountNumber.setInt(2, pin);

            ResultSet resultSet = statementAccountNumber.executeQuery();

            while (resultSet.next()){
                PrivateData.setAccount_detail(resultSet.getInt("Account_Number") , resultSet.getInt("pin"));
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            return false;
        }
    }
}
