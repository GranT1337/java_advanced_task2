package repository;

import entity.Account;
import service.AccountService;

import java.util.List;

public class AccountRepository {

    private static AccountRepository instance;

    private List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    public AccountRepository() {
        accountList = new AccountService().readAccountsFromFile();
    }

    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    public static int getSizeAccountList() {
        return instance.getAccountList().size();
    }
}
