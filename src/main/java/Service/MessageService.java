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
 

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
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
