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

  /*    }

    public String getAccountByUsername(String username) throws SQLException{
        return accountDAO.getAccUsername(username);
    }
    
    public List<Account> verifyAccount (Account account) throws SQLException{
        return accountDAO.accountVerified(account);
    }

    public int getAccountById(int account_id) {
        return accountDAO.getAccountID(account_id);
    }
} */
    

    //hmmm

   

    public Account createAccount(Account account) {
        //checking if username is not empty, or password is less than 4 long, and making sure accnt does not exist.
       if (account.getUsername().length() != 0 || account.getPassword().length() < 4 || accountDAO.insertAccount(account) != null) {
        return this.accountDAO.insertAccount(account); //looks back at DAO class for the method's name you would want to use
       }
       return null;
    }

    public Account getAccountById(int posted_by) {
        return this.accountDAO.getAccount_id(posted_by);
    }

    public Account authenticatedAccount(String username, String password) {
        return null;
    }

    public Account getAccountByUsername(String username) {
        return accountDAO.getUsername(username);
    }



}
