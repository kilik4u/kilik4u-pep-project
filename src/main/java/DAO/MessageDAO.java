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
            String sql = "INSERT INTO message (posted_by, message_text,)"
        }
    }


    /*      
     * 
     */

     public List<Message> getAllMessages(){
        Connection connection = Util.ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql ="SELECT * FROM Message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message();
                messages.add(message);
            }
        }catch(SQLException e){
                System.out.println(e.getMessage());
            }
            return messages;
        
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

    public Message getMessageByMessageId(int message_id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM Message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message_id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Message message = new Message();
                return message;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
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

     public Message deleteMessage(int id) {
        Connection connection = ConnectionUtil.getConnection();
        Message deleteMessage = getMessageByMessageId(id);
        if(deleteMessage == null) {
            return null;
        }
        try {
            String sql = "DELETE FROM Message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return deleteMessage;
     }

     public Message patchMessage(int id, String text) {
        Connection connection = ConnectionUtil.getConnection();
        Message patchMessage = getMessageByMessageId(id);
        if(patchMessage == null) 
        return null;
        try {
            String sql = "UPDATE Message SET message_text = ? WHERE message_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, text);
            preparedStatement.setInt(2, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                                            rs.getInt("posted_by"),
                                            rs.getString("message_text"),
                                            rs.getLong("time_posted_epoch"));
                                            return message;
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
     }

    public Message insertMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO Message(posted_by,message_text,time_posted_epoch) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

           
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
           
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                int generated_message_id = (int) rs.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    
    return null;
}
}
