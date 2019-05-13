package service;

import entity.Account;
import entity.Transfer;
import exception.NotEnoughMoneyException;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class TransactionService {

    private final static Logger logger = Logger.getLogger(TransactionService.class);
    private static TransactionService instance;

    public static TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionService();
        }
        return instance;
    }

    public void transferMoney(Transfer transfer) throws NotEnoughMoneyException {
        Account sender = transfer.getAccountOne();
        Account recipient = transfer.getAccountTwo();
        long amountMoney = transfer.getAmountMoney();

        ReentrantLock firstLock = sender.getId() < recipient.getId() ? sender.getLock() : recipient.getLock();
        ReentrantLock secondLock = sender.getId() > recipient.getId() ? sender.getLock() : recipient.getLock();

        try {
            firstLock.lock();
            secondLock.lock();

            logger.info("Transfer from " + sender + " to " + recipient + ". Amount of money " + amountMoney + "\n");
            Counters.commonCounter.getAndIncrement();
            if (sender.getBalance() < amountMoney) {
                Counters.counterOfFailedOperations.getAndIncrement();
                throw new NotEnoughMoneyException(sender + " don't have " + amountMoney);
            }

            sender.setBalance(sender.getBalance() - amountMoney);
            recipient.setBalance(recipient.getBalance() + amountMoney);
        } finally {
            firstLock.unlock();
            secondLock.unlock();
        }
    }




}
