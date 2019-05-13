import entity.Account;
import org.apache.log4j.Logger;
import service.Counters;
import utils.AccountGenerator;
import repository.AccountRepository;
import service.AccountService;
import thread.ThreadTransaction;
import utils.TransactionGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {

    private static final int NUMBER_OF_THREADS = 20;
    private static final int NUMBER_OF_TRANSACTION = 1000;
    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        AccountGenerator accountGenerator = new AccountGenerator();
        AccountService accountService = new AccountService();
        TransactionGenerator transactionGenerator = new TransactionGenerator();

        accountGenerator.createRandomAccounts();
        List<Account> accountsList = AccountRepository.getInstance().getAccountList();

        logger.info("Все аккаунты до переводов: " + accountsList + "\n" );

        long sumBefore = accountService.getSumOnAllAccounts(accountsList);


        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        IntStream.range(0,NUMBER_OF_TRANSACTION).forEach(i -> executorService.submit(
                new ThreadTransaction(transactionGenerator.getPairAccountAndAmount()))
        );

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        logger.info("Все аккаунты после переводов: " + accountsList + "\n");
        logger.info("Всего операций: " + Counters.commonCounter.get());
        logger.info("Неудачных операций: " + Counters.counterOfFailedOperations.get());
        long sumAfter = accountService.getSumOnAllAccounts(accountsList);
        logger.info("Balance amount before transfers " + sumBefore);
        logger.info("Balance amount after transfers  " + sumAfter);
    }
}
