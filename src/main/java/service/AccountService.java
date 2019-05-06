package service;

import entity.Account;
import utils.AccountGenerator;
import repository.AccountRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class AccountService {

    public List<Account> readAccountsFromFile() {
        IntStream stream = IntStream.range(0, AccountGenerator.NUMBER_OF_ACCOUNTS);
        List<Account> listAccounts = new ArrayList<>();

        stream.forEach(i -> {
            File file = new File("accounts\\" + i + ".bin");
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

                Account account = (Account) ois.readObject();
                listAccounts.add(account);

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        });
        return listAccounts;
    }


    public long getSumOnAllAccounts(List<Account> accountList) {
        return accountList.stream().mapToLong(Account::getBalance).sum();
    }


    public long subtractMoney(long amount, Account account) {
        long newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        return newBalance;
    }


}
