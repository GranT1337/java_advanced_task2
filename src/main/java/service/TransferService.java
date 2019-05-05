package service;

import entity.Account;
import org.apache.log4j.Logger;

public class TransferService {

    private Logger logger = Logger.getLogger(TransferService.class);

    public void transfer (Account from, Account to, long amountMoney) {

        if (from.equals(to)) {
            logger.info("You cannot transfer money to yourself");
            return;
        }

        if (from.getBalance() < amountMoney ) {
            logger.info("Insufficient funds");
            return;
        }





    }
}
