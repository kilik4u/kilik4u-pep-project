package Service;

import java.util.List;
import java.util.*;


import DAO.MessageDAO;
import Model.Message;
import Model.Account;
//import DAO.AccountDAO;
public class MessageService {
    public MessageDAO messageDAO;
   

    public MessageService() {
        messageDAO = new MessageDAO(); 
    }

    public Message createMessage(Message message) {
        //creating new message 
        return messageDAO.create(message);
    }

     public List<Message> findAllMessages() {
        return messageDAO.getAllMessages();
     }
     public Message getMessageById(int message_id) {
        return messageDAO.findById(message_id);
     }
     public List<Message> findByUserId(int userId) {
        return messageDAO.findByUserId(userId);
     }
     public Message deleteMessage(int message_id) {
        Message deletedMessage = messageDAO.findById(message_id);
        if (deletedMessage != null) {
            boolean succ = messageDAO.deleteById(message_id);
            if (succ) {
                return deletedMessage;
            }
        }
        return null;
     }
     public Message updateMessage(int message_id, String updatedMessageText) {
        if (updatedMessageText != null && !updatedMessageText.trim().isEmpty()) {
            Message existingMessage = messageDAO.findById(message_id);
    
            if (existingMessage != null) {
                existingMessage.setMessage_text(updatedMessageText);
                return messageDAO.update(existingMessage);
            }
        }
        return null;
    }
}
