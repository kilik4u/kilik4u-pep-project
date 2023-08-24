package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;
import Model.Account;
//import Model.Message;
//import Service.AccountService;
import Util.ConnectionUtil;
//THIS needs getAccount
public class AccountDAO {
/* 
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
   */
        //hmmmmm

        public Account insertAccount(Account account){
            Connection connection = ConnectionUtil.getConnection();
            try {
                String sql = "INSERT INTO account (username, password) VALUES (?,?)";
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

        public Account getAccount_id(int accountId) {
            Connection connection = ConnectionUtil.getConnection();
            try {
                String sql = "SELECT * FROM account WHERE account_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
                preparedStatement.setInt(1,accountId);
               
    
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()) {
                   String username = rs.getString("username");
                   String password = rs.getString("password");
                   return new Account(accountId, username, password);
                }
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }
        public Account getAccountByUsername(String username, String password) {
            Connection connection = ConnectionUtil.getConnection();
            try {
                String sql = "SELECT * FROM account WHERE username = ? AND password = ?"; //didnt have AND PASSWORD = ?
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                //didnt have preparedStatement SetSTring password before.
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int accountId = resultSet.getInt("account_id");
                    //String password = resultSet.getString("password");
                    return new Account(accountId, username, password);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
       // public Account getUsername(String username) {
         //   return null;
        //}

        //public Account getAccount(String username, String password) {
        //return null;
        //}

     //   public Account getAuthenticatedAccount(String username, String password) {
       //     return null;
        //}
        

	
}
