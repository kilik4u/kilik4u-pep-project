package Service;

import java.util.List;

import DAO.MessageDAO;

import java.util.*;


import Model.Message;
import Model.Account;

public class MessageService {
    public MessageDAO messageDAO;
   

    public MessageService() {
        messageDAO = new MessageDAO(); 
    }

    public Message createMessage(Message message) {
        
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
     public Message updateMessage(int message_id, String updatedText) {
            try {
                if (updatedText.isEmpty() || updatedText.length() > 254 || messageDAO.findById(message_id) == null) {
                    return null;
                } else {
                    messageDAO.update(message_id, updatedText);
                    return messageDAO.findById(message_id);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
    
        
       // if (updatedMessageText.isEmpty() || updatedMessageText.length() > 254 || messageDAO.findById(message_id) == null) {
         //   System.out.println("Service ok");
           // return null;
        //} else {
          //messageDAO.update(message_id, updatedMessageText);
            //  Message existingMessage = messageDAO.findById(message_id);
          //  if (existingMessage != null) {
               
              //  System.out.println("service Existing");
            //    existingMessage.setMessage_text(updatedMessageText);
              
                //return messageDAO.update(existingMessage);
            
        
         
      
  //  } 
/*public Message updateMessage(int message_id, String updatedMessageText) {
    if (updatedMessageText != null && !updatedMessageText.isEmpty()) {
        Message existingMessage = messageDAO.findById(message_id);
        if (existingMessage != null) {
            existingMessage.setMessage_text(updatedMessageText);
            return messageDAO.u
        return null;pdate(existingMessage);
        }
    }
    return null;
}
 */
}
}

