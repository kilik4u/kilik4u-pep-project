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

  
    

    //hmmm

    public Account getAccountByUsername(String username) {
        return null;
    }

    public Account createAccount(Account account) {
        //checking if username is not empty, or password is at least 4 or greater long, and making sure accnt does not exist.
       if (account.getUsername().length() != 0 || account.getPassword().length() >= 4 || accountDAO.insertAccount(account) != null) {
        return this.accountDAO.insertAccount(account); //looks back at DAO class for the method's name you would want to use
       }
       return null;
    }

    public Account getAccountById(int posted_by) {
        return null;
    }

    public Account authenticatedAccount(String username, String password) {
        return null;
    }



}
