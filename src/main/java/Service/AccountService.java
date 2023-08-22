package Service;

import java.util.List;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public List<Account> getAllAccounts(){
        return accountDAO.getAllAccounts();
    }

    public Account addAccount(Account account) {
        return accountDAO.insertAccount(account);
    }
    /*
     * public class AuthorService {
    private AuthorDAO authorDAO;
    
     * no-args constructor for creating a new AuthorService with a new AuthorDAO.
     * There is no need to change this constructor.
     
    public AuthorService(){
        authorDAO = new AuthorDAO();
    }
     public AuthorService(AuthorDAO authorDAO){
        this.authorDAO = authorDAO;
    }
    /**
     * TODO: Use the AuthorDAO to retrieve all authors.
     *
     * @return all authors
     */
   // public List<Author> getAllAuthors() {
     //   return authorDAO.getAllAuthors();
    //}
    //public Author addAuthor(Author author) {
        //return authorDAO.insertAuthor(author);
    //}
    // */



}
