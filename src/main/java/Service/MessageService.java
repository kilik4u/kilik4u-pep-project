package Service;

import java.util.List;
import java.util.*;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public MessageDAO messageDAO;

    /*  public Book addBook(Book book) {
        Book exist = bookDAO.getBookByIsbn(book.getIsbn());
        if (exist != null) {
            return null;
        } else {
        return bookDAO.insertBook(book);
    }
}
     * 
     */

    public MessageService() {
        messageDAO = new MessageDAO(); 
    }
 
    public Message InsertMessage(Message message) {
        if(message.getMessage_text().length() == 0 || message.getMessage_text().length() > 254 || message.getPosted_by() != accountDAO.getAccount_id(message.getPosted_by())) {
            return null;
        } else 
        return messageDAO.insertMessage(message);
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message deleteMessage(int id) {
        return messageDAO.deleteMessage(id);
    }

    public Message patchMessage(int id, String text) {
        if(text.length() == 0 || text.length() > 255) 
        return messageDAO.patchM
    }

     public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
     }
     public Message addMessage(Message message) {
        Message exist = messageDAO.getMessageByMessageId(message.getMessage_id());
        if(exist != null) {
            return null;
        } else{
            return messageDAO.insertMessage(message);
        }
     }
}
