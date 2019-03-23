package nl.han.oose.persistence;

import nl.han.oose.entity.account.AccountToken;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Default
public class TokenDAO {
    private ConnectionFactory connectionFactory;
    private final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public TokenDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public AccountToken generateNewToken(String username) {
        AccountToken token = null;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO accountTokens (token, username, expiryDate) VALUES (?, ?, ?)"
                )
        ) {
            String generatedToken = UUID.randomUUID().toString();
            String timeStamp = new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTimeInMillis() + 28800000);

            preparedStatement.setString(1, generatedToken);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, timeStamp);
            preparedStatement.execute();
            token = new AccountToken(generatedToken, username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return token;
    }

    public boolean checkIfTokenIsValid(String token) {
        boolean tokenExpired = false;
        String expiryDateString = null;

        try(
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT expiryDate FROM accounttokens WHERE token = ?")
                ) {
            statement.setString(1, token);
            ResultSet resultset = statement.executeQuery();

            while(resultset.next()){
                expiryDateString = resultset.getString("expiryDate");
            }
            Date expireTime = new SimpleDateFormat(dateFormat).parse(expiryDateString);
            String currentTime = new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTime());
            Date timeStamp = new SimpleDateFormat(dateFormat).parse(currentTime);
            tokenExpired = timeStamp.after(expireTime);
        } catch (SQLException | ParseException e){
            e.printStackTrace();
        }
        return tokenExpired;
    }

    public void deleteToken(String token){
        try(
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM accounttokens WHERE token = ?")
                ){
            statement.setString(1, token);
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
