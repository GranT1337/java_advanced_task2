package service;

import entity.Account;
import org.apache.log4j.Logger;
import utils.TransactionGenerator;

import java.util.List;

public class TransactionService {

    private Logger logger = Logger.getLogger(TransactionService.class);


//    public void transfer () {
//        TransactionGenerator transactionGenerator = new TransactionGenerator();
//        List<Account> pairAccount = transactionGenerator.getPairAccount();
//
//        long amountMoney = transactionGenerator.generateAmountTransaction();
//
//        Account from = pairAccount.get(0);
//        Account to = pairAccount.get(1);
//
//
//        System.out.println(amountMoney);
//        System.out.println(from);
//        System.out.println(to);
//        StaticClass.a++;
//
//
//        if (from.getBalance() < amountMoney ) {
//            logger.info("Insufficient funds");
//            return;
//        }
//
//
//        from.setBalance(from.getBalance() - amountMoney);
//        to.setBalance(to.getBalance() + amountMoney);
//
//        from.getLock().unlock();
//        to.getLock().unlock();
//    }
}
