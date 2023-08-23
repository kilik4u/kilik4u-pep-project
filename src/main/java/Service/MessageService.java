package Service;

import java.util.List;
import java.util.*;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;
import Model.Account;

public class MessageService {
    public MessageDAO messageDAO;
    public AccountDAO accountDAO;

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
        accountDAO = new AccountDAO();
    }
 
    public Message InsertMessage(Message message) {
        if((message.getMessage_text()).length() < 1 || (message.getMessage_text()).length() >= 255) { //|| message.getPosted_by() != accountDAO.getAccount(message.getPosted_by())) { //was accountDAO.getAccount_id
            return null;
        } else if(message.getPosted_by() != (accountDAO.getAccount_id(message.getPosted_by()))) {
            return null;
        }
            return messageDAO.insertMessage(message);
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO) {
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }

    public Message deleteMessage(int id) {
        return messageDAO.deleteMessage(id);
    }

    public Message patchMessage(int id, String text) {
        if(text.length() == 0 || text.length() > 255) 
        return messageDAO.patchMessage(id, text);
        else return null;
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

     public Message getMessageByMessageId(int id) {
        return messageDAO.getMessageByMessageId(id);
     }
}
