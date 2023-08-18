package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import DAO.AccountDAO;
import Service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountDAO accountDAO = new AccountDAO();
    AccountService accountService = new AccountService(accountDAO);
   
    // MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = new accountService();
       // this.messageService = new messageService();
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
     */
    private void registerHandler(Context context) {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account newAccount = accountService.addAccount(account);
        if(newAccount!=null) {
            //context.json(mapper.writeValueAsString(newAccount));
            System.out.println(newAccount);
            context.json(newAccount);
            context.status(200);

        } else {
            context.status(400);
        }
    }

    private void postMessageHandler(Context context) {

    }

    private void getAllMessagesHandler(Context context) {
        context.json(" ");
    }

    private void loginHandler(Context context) {

    }

    private void messageByIdHandler(Context context) {

    }

    private void deleteMessageHandler(Context context) {

    }

    private void patchMessageHandler(Context context) {

    }

    private void getAllMessagesByIdHandler(Context context) {

    }    

   

}
