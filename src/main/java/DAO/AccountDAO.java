package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;
import Model.Account;
import Model.Message;
import Util.ConnectionUtil;
//THIS needs getAccount
public class AccountDAO {

    public List<Account> getAllAccounts(){
        Connection connection = ConnectionUtil.getConnection();
        List<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Account";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                accounts.add(account);
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return accounts;
    }

    public Account getAccount_id(int account_id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM Account WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1,account_id);
           

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
               String username = rs.getString("username");
               String password = rs.getString("password");
               return new Account(account_id, username, password);
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

        public Account insertAccount(Account account){
            Connection connection = ConnectionUtil.getConnection();
            try {
                String sql = "INSERT INTO Account (username, password) VALUES (?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, account.getUsername());
                preparedStatement.setString(2, account.getPassword());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()){
                    int generated_account_id = (int) rs.getLong(1);
                    return new Account(generated_account_id, account.getUsername(), account.getPassword());
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }
    
        public Account getAccount(Account account) {
            Connection connection = ConnectionUtil.getConnection();
            try {
                String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, account.getUsername());
                preparedStatement.setString(2, account.getPassword());
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()) {
                    return new Account(rs.getInt("account_id"), rs.getString("username"),rs.getString("password"));
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

        public List<Message> getMessagesByAccountId(int account_id) {
            List<Message> messages = new ArrayList<>();
            Connection connection = ConnectionUtil.getConnection();

            try {
                String sql = "SELECT * FROM Message WHERE posted_by = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, account_id);

                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()) {
                    int message_id = rs.getInt("message_id");
                    String message_text = rs.getString("message_text");
                    long time_posted_epoch = rs.getLong("time_posted_epoch");

                    Message message = new Message(message_id, account_id, message_text, time_posted_epoch);
                    messages.add(message);
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return messages;
        }
    /*  // Method to retrieve messages by account ID from the database
    private static List<Message> getMessagesByAccountId(int accountId) {
        List<Message> messages = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/social_media_db", "username", "password")) {
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int messageId = rs.getInt("message_id");
                String messageText = rs.getString("message_text");
                long timePostedEpoch = rs.getLong("time_posted_epoch");

                Message message = new Message(messageId, accountId, messageText, timePostedEpoch);
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

     public Author insertAuthor(Author author){
        Connection connection = ConnectionUtil.getConnection();
        try {
//          Write SQL logic here. You should only be inserting with the name column, so that the database may
//          automatically generate a primary key.
            String sql = "INSERT INTO Author (name) VALUES (?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString method here.
            preparedStatement.setString(1, author.getName());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_author_id = (int) pkeyResultSet.getLong(1);
                return new Author(generated_author_id, author.getName());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

     *  public List<Author> getAllAuthors(){
        Connection connection = ConnectionUtil.getConnection();
        List<Author> authors = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM Author";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Author author = new Author(rs.getInt("id"), rs.getString("name"));
                authors.add(author);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return authors;
    }
     */

	
}
