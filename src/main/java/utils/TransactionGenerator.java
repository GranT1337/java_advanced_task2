package utils;

import entity.Account;
import repository.AccountRepository;
import service.StaticClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionGenerator {

    public long generateAmountTransaction () {
        return ThreadLocalRandom.current().nextLong(2000);
    }

    public void getPairAccount() {
        Random random = new Random();
        List<Account> listAccounts = AccountRepository.getInstance().getAccountList();

        int accountFrom = random.nextInt(listAccounts.size());
        int accountTo = random.nextInt(listAccounts.size());

        if (accountFrom == accountTo) {
            while (accountFrom == accountTo) {
                accountFrom = random.nextInt(listAccounts.size());
            }
        }


        ReentrantLock firstLock = listAccounts.get(accountFrom).getId() < listAccounts.get(accountTo).getId() ? listAccounts.get(accountFrom).getLock() : listAccounts.get(accountTo).getLock();
        ReentrantLock secondLock = listAccounts.get(accountFrom).getId() > listAccounts.get(accountTo).getId() ? listAccounts.get(accountFrom).getLock() : listAccounts.get(accountTo).getLock();


        firstLock.lock();
        secondLock.lock();

        long amountMoney = generateAmountTransaction();

        System.out.println(amountMoney);
        System.out.println(listAccounts.get(accountFrom));
        System.out.println(listAccounts.get(accountTo));
        StaticClass.commonCounter.getAndIncrement();


        if (listAccounts.get(accountFrom).getBalance() < amountMoney ) {
            StaticClass.b++;
            firstLock.unlock();
            secondLock.unlock();
            System.out.println("Insufficient funds");
            return;
        }


        listAccounts.get(accountFrom).setBalance(listAccounts.get(accountFrom).getBalance() - amountMoney);
        listAccounts.get(accountTo).setBalance(listAccounts.get(accountTo).getBalance() + amountMoney);

        firstLock.unlock();
        secondLock.unlock();

    }
}
