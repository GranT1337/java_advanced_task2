package service;

import entity.Account;
import org.apache.log4j.Logger;
import utils.TransactionGenerator;

public class TransactionService {

    private Logger logger = Logger.getLogger(TransactionService.class);


    public void transfer () {
        TransactionGenerator transactionGenerator = new TransactionGenerator();
        long amountMoney = transactionGenerator.generateAmountTransaction();
        Account from = transactionGenerator.getPairAccount().get(0);
        Account to = transactionGenerator.getPairAccount().get(1);


        System.out.println(amountMoney);
        System.out.println(from);
        System.out.println(to);


        if (from.getBalance() < amountMoney ) {
            logger.info("Insufficient funds");
            return;
        }


        from.setBalance(from.getBalance() - amountMoney);
        to.setBalance(to.getBalance() + amountMoney);

    }






}
