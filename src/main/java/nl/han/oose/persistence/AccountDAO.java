package nl.han.oose.persistence;

import nl.han.oose.entity.account.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    private ConnectionFactory connectionFactory;

    public AccountDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public Account getUserAccount(String username) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM useraccounts WHERE username = ?"
                )
        ) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new Account(resultSet.getString("username"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUsernameThroughToken(String token) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT username FROM accounttokens WHERE token = ?"
                )
        ) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                return resultSet.getString("username");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
