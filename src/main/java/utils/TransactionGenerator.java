package utils;

import entity.Account;
import repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TransactionGenerator {

    public long generateAmountTransaction () {
        return ThreadLocalRandom.current().nextLong(2000);
    }
    //return Math.round(Math.random() * 1000);



    public List<Account> getPairAccount() {
        Random random = new Random();
        List<Account> pairAccount = new ArrayList<>();
        List<Account> listAccounts = AccountRepository.getInstance().getAccountList();

        int accountFrom = random.nextInt(listAccounts.size());
        int accountTo = random.nextInt(listAccounts.size());

        if (accountFrom == accountTo) {
            while (accountFrom == accountTo) {
                accountFrom = random.nextInt(listAccounts.size());
            }
        }

        pairAccount.add(listAccounts.get(accountFrom));
        pairAccount.add(listAccounts.get(accountTo));

        return pairAccount;
    }


}
