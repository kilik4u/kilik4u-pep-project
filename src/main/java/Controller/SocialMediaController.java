package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import DAO.AccountDAO;
import DAO.MessageDAO;
import Service.AccountService;
import Service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    //AccountDAO accountDAO; //= new AccountDAO();
    AccountService accountService; //= new AccountService(accountDAO);
    // MessageDAO messageDAO;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerHandler);
        //app.post("/register", this::postRegisterHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        //app.post("/messages", this::)
        app.post("/login", this::loginHandler);
        app.get("/messages/{message_id}", this::messageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByIdHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
 
     * 
     */
    
    private void registerHandler(Context context) {
        AccountService accountService = new AccountService();
        ObjectMapper mapper = new ObjectMapper();
        Account account = context.bodyAsClass(Account.class);
        
        if(account.getUsername() == null || account.getPassword() == null || account.getPassword().length() < 4)  {
            //context.json(mapper.writeValueAsString(newAccount));
            System.out.println("hi");
            context.status(400).json("");
            return;
        } 
        Account existingAccount = accountService.getAccountByUsername(account.getUsername());
        if(existingAccount != null) {
            context.status(400).json("");
            return;
        }
        Account createdAccount = accountService.createAccount(account);
        if(createdAccount != null) {
            context.status(200).json(createdAccount);
        }else{
            context.status(400).json("");
        }
        }
    

    private void createMessageHandler(Context context) {
        MessageService messageService = new MessageService();
        Message message = context.bodyAsClass(Message.class);
        
        AccountService accountService = new AccountService();
        Account postedByAccount = accountService.getAccountById(message.getPosted_by());

        if(postedByAccount == null) {
            context.status(400).json("");
            return ;
        }
        if (message.getMessage_text() == null || message.getMessage_text().trim().isEmpty() || message.getMessage_text().length() > 254) {
            context.status(400).json("");
            return ;
        }
        Message createdMessage = messageService.createMessage(message);
        if (createdMessage != null) {
            context.status(200).json(createdMessage);
        } else {
            context.status(400).json("");
        }

    }

    private void getAllMessagesHandler(Context context) {
        MessageService messageService = new MessageService();
        List<Message> messages = messageService.getAllMessages();
    
        if (messages != null && !messages.isEmpty()) {
            context.status(200).json(messages); 
        } else {
            context.status(200).json(new ArrayList<Message>());
        }
    }

    private void loginHandler(Context context)  {
        AccountService accountService = new AccountService();
        Account account = context.bodyAsClass(Account.class);
        Account authenticatedAccount = accountService.authenticatedAccount(account.getUsername(), account.getPassword());
        if(authenticatedAccount != null) {
            context.json(authenticatedAccount);
        }else{
            context.status(401).json("");
        }
        

    }

    private void messageByIdHandler(Context context)  {
        MessageService messageService = new MessageService();
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.getMessageById(messageId);
    
        if (message != null) {
            context.json(message);
        } else {
            context.status(200).json(""); 
        }
        
    }

    private void deleteMessageHandler(Context context) {
        int fetchID = Integer.parseInt(context.pathParam("message_id"));
        if(messageService.deleteMessage(fetchID) != null)
        context.json(messageService.deleteMessage(fetchID));
    }

    private void updateMessageHandler(Context context) {
        String patchText = context.body();
        int fetchID = Integer.parseInt(context.pathParam("message_id"));
        Message patchMessage = messageService.patchMessage(fetchID, patchText);
        if(patchMessage != null) {
            context.json(patchMessage);
        } else {
            context.status(400);
        }
    }

    private void getAllMessagesByIdHandler(Context context) {
        //int account_id = Integer.parseInt(context.pathParam("account_id"));
        //List<Message> messages = getMessagesByAccountId(account_id);
    }    

   

}
