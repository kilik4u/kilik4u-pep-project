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
     *    Javalin app = Javalin.create();
        app.get("/books", this::getAllBooksHandler);
        app.post("/books", this::postBookHandler);
        app.get("/authors", this::getAllAuthorsHandler);
        app.post("/authors", this::postAuthorHandler);
        app.get("/books/available", this::getAvailableBooksHandler);
        app.start(8080);
    }
    logins, registrations, message creations, message updates, and message deletions.


     */

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::registerHandler);
        //app.post("/register", this::postRegisterHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        //app.post("/messages", this::)
        app.post("/login", this::loginHandler);
        app.get("/messages/{message_id}", this::messageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.patch("/messages/{message_id}", this::patchMessageHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByIdHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     *     private void postAuthorHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Author author = mapper.readValue(ctx.body(), Author.class);
        Author addedAuthor = authorService.addAuthor(author);
        if(addedAuthor!=null){
            ctx.json(mapper.writeValueAsString(addedAuthor));
        }else{
            ctx.status(400);
        }
    }
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    
    private void registerHandler(Context context) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account newAccount = accountService.addAccount(account);
        if(newAccount == null) {
            //context.json(mapper.writeValueAsString(newAccount));
            System.out.println("hi");
            context.status(400);

        } else {
            System.out.println("Bye");
            System.out.println(newAccount);
            context.json(newAccount);
            context.status(200);
        }
    }

    private void postMessageHandler(Context context) throws JsonProcessingException {
        /* As a user, I should be able to submit a new post on the endpoint POST localhost:8080/messages. The request body will contain a JSON representation of a message, which should be persisted to the database, but will not contain a message_id.

- The creation of the message will be successful if and only if the message_text is not blank, is under 255 characters, and posted_by refers to a real, existing user. If successful, the response body should contain a JSON of the message, including its message_id. The response status should be 200, which is the default. The new message should be persisted to the database.
- If the creation of the message is not successful, the response status should be 400. (Client error)

          private void postBookHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Book book = mapper.readValue(ctx.body(), Book.class);
        Book addedBook = bookService.addBook(book);
        if(addedBook!=null){
            ctx.json(mapper.writeValueAsString(addedBook));
        }else{
            ctx.status(400);
        }
    } */
    ObjectMapper mapper = new ObjectMapper();
    Message message = mapper.readValue(context.body(), Message.class);
    Message newMessage = messageService.addMessage(message);
    if(newMessage == null) {
        context.status(400);
    }else{
        context.json(newMessage);
    }

    }

    private void getAllMessagesHandler(Context context) {
       //   private void getAllAuthorsHandler(Context ctx) {
       // List<Author> authors = authorService.getAllAuthors();
       // ctx.json(authors);
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
       
    }

    private void loginHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account gotAccount = accountService.getAccount(account);
        if(gotAccount != null) {
            context.json(gotAccount);
            context.status(200);
        }else 
            context.status(401);
        

    }

    private void messageByIdHandler(Context context) throws JsonProcessingException {
        int fetchID = Integer.parseInt(context.pathParam("message_id"));
        Message fetchMessage = messageService.getMessageByMessageId(fetchID);
        if(fetchMessage != null) {
            context.json(fetchMessage);
        }
    }

    private void deleteMessageHandler(Context context) {
        int fetchID = Integer.parseInt(context.pathParam("message_id"));
        if(messageService.deleteMessage(fetchID) != null)
        context.json(messageService.deleteMessage(fetchID));
    }

    private void patchMessageHandler(Context context) {
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
        int account_id = Integer.parseInt(context.pathParam("account_id"));
        List<Message> messages = getMessagesByAccountId(account_id);
    }    

   

}
