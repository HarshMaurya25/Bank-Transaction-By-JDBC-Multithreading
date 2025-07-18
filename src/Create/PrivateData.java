package Create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PrivateData {
    private static final String password = "password";
    private static final HashMap<Integer, Integer> account_detail = new HashMap();
    private static final String query = "SELECT * FROM account";
    private static PreparedStatement preparedStatement;

    public PrivateData(Connection connection) {
        try {
            PrivateData.preparedStatement =connection.prepareStatement(PrivateData.query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                account_detail.put(resultSet.getInt("Account_number") , resultSet.getInt("pin"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<Integer, Integer> getAccount_detail() {
        return account_detail;
    }

    public static void setAccount_detail(int id, Integer pincode) {
        account_detail.put(id, pincode);
    }

    public static String GetPassWord(){
        return password;
    }
}
