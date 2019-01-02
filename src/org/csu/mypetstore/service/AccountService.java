package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.impl.AccountDAOImpl;

public class AccountService {


  private AccountDAO accountDAO=new AccountDAOImpl();

  public Account getAccount(String username) {
    return accountDAO.getAccountByUsername(username);
  }

  public Account getAccount(String username, String password) {
    Account account = new Account();
    account.setUsername(username);
    account.setPassword(password);
    return accountDAO.getAccountByUsernameAndPassword(account);
  }

  public void insertAccount(Account account) {
    accountDAO.insertAccount(account);
    accountDAO.insertProfile(account);
    accountDAO.insertSignon(account);
  }


  public void updateAccount(Account account) {
    accountDAO.updateAccount(account);
    accountDAO.updateProfile(account);

    if (account.getPassword() != null && account.getPassword().length() > 0) {
      accountDAO.updateSignon(account);
    }
  }

  public boolean usernameIsExist(Account account) {
    if(accountDAO.getAccountByUsername(account.getUsername())!=null)return true;
    else return false;
  }
}
