package service;

import entity.Account;
import entity.Transfer;
import org.apache.log4j.Logger;
import repository.AccountRepository;
import utils.TransactionGenerator;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionService {

    private Logger logger = Logger.getLogger(TransactionService.class);
    private static TransactionService instance;

    public static TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionService();
        }
        return instance;
    }

    public void transferMoney(Transfer transfer) {

        Account sender = transfer.getAccountOne();
        Account recipient = transfer.getAccountTwo();
        long amountMoney = transfer.getAmountMoney();

        ReentrantLock firstLock = sender.getId() < recipient.getId() ? sender.getLock() : recipient.getLock();
        ReentrantLock secondLock = sender.getId() > recipient.getId() ? sender.getLock() : recipient.getLock();

        firstLock.lock();
        secondLock.lock();

        System.out.println(amountMoney);
        System.out.println(sender);
        System.out.println(recipient);
        StaticClass.commonCounter.getAndIncrement();


        if (sender.getBalance() < amountMoney ) {
            StaticClass.counterOfFailedOperations.getAndIncrement();
            firstLock.unlock();
            secondLock.unlock();
            System.out.println("Insufficient funds");
            return;
        }


        sender.setBalance(sender.getBalance() - amountMoney);
        recipient.setBalance(recipient.getBalance() + amountMoney);

        firstLock.unlock();
        secondLock.unlock();
    }


}
