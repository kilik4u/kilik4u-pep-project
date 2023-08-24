package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import Model.Message;
import Util.ConnectionUtil;

//need an InsertMessage, for MessageService class.


public class MessageDAO {

    public Message create(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected == 1) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if(generatedKeys.next()) {
                        int messageId = generatedKeys.getInt(1);
                        message.setMessage_id(messageId);
                        return message;
                    }
                }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*      
     * 
     */

     public List<Message> getAllMessages(){
        Connection connection = Util.ConnectionUtil.getConnection();
        try {
            List<Message> messages = new ArrayList<>();
            String sql = "SELECT * FROM message";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int messageId = resultSet.getInt("message_id");
                int postedBy = resultSet.getInt("posted_by");
                String messageText = resultSet.getString("message_text");
                long timePostedEpoch = resultSet.getLong("time_posted_epoch");

                Message message = new Message(messageId, postedBy, messageText, timePostedEpoch);
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return null;
     } 

     /*   public Book getBookByIsbn(int isbn){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM Book WHERE isbn = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setInt method here.
            preparedStatement.setInt(1, isbn);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Book book = new Book(rs.getInt("isbn"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getInt("copies_available"));
                return book;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    } */

    public Message findById(int message_id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, message_id);
    
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int postedBy = resultSet.getInt("posted_by");
                String messageText = resultSet.getString("message_text");
                long timePostedEpoch = resultSet.getLong("time_posted_epoch");
    
                return new Message(message_id, postedBy, messageText, timePostedEpoch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    
        return null;
    }
   
    public List<Message> findByUserId(int userId) {
        Connection connection = ConnectionUtil.getConnection();
        
    
        try {
            
            List<Message> messages = new ArrayList<>();
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
    
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int messageId = resultSet.getInt("message_id");
                int postedBy = resultSet.getInt("posted_by");
                String messageText = resultSet.getString("message_text");
                long timePostedEpoch = resultSet.getLong("time_posted_epoch");
    
                Message message = new Message(messageId, postedBy, messageText, timePostedEpoch);
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        } 
        return new ArrayList<>();
    }
    /*
     *    public Book insertBook(Book book){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "INSERT INTO Book(isbn, author_id, title, copies_available) VALUES (?,?,?,?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1, book.getIsbn());
            preparedStatement.setInt(2, book.getAuthor_id());
            preparedStatement.setString(3, book.getTitle());
            preparedStatement.setInt(4, book.getCopies_available());
            preparedStatement.executeUpdate();
            return book;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
     */
    public boolean deleteById(int messageId) {
        Connection connection = ConnectionUtil.getConnection();
        
        
        try {
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, messageId);
    
            int rowsDeleted = statement.executeUpdate();
    
            if (rowsDeleted == 0) {
                // Handle the case where no rows were deleted (e.g., message with the specified ID doesn't exist)
                return false;
            } else {
                // Handle the successful deletion
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return false; // Return false in case of an exception
        } 
        
    }

    public Message update(Message message ) {
        Connection connection = ConnectionUtil.getConnection();

        Message updatedMessage = null;
        try {
            String sql = "UPDATE message SET posted_by = ?, message_text = ?, time_posted_epoch = ? WHERE message_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
        
            statement.setInt(1, message.getPosted_by());
            statement.setString(2, message.getMessage_text());
            statement.setLong(3, message.getTime_posted_epoch());
            statement.setInt(4, message.getMessage_id());

            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
                
                // Handle the case where no rows were updated (e.g., message with the specified ID doesn't exist)
                return null;
                
            
            } else {
                return message ;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return updatedMessage ;
    }
    

   

}
