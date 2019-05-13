package utils;

import entity.Account;
import entity.Transfer;
import repository.AccountRepository;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class TransactionGenerator {

    public long generateAmountTransaction () {
        return ThreadLocalRandom.current().nextLong(2000);
    }

    public Transfer getPairAccountAndAmount() {
        List<Account> listAccounts = AccountRepository.getInstance().getAccountList();

        Random random = new Random();
        int accountFrom = random.nextInt(AccountRepository.getSizeAccountList());
        int accountTo = random.nextInt(AccountRepository.getSizeAccountList());
        long amountMoney = generateAmountTransaction();

        while (accountFrom == accountTo) {
            accountFrom = random.nextInt(AccountRepository.getSizeAccountList());
        }
        return new Transfer(listAccounts.get(accountFrom), listAccounts.get(accountTo), amountMoney);
    }
}
