import entity.Account;
import service.StaticClass;
import utils.AccountGenerator;
import repository.AccountRepository;
import service.AccountService;
import thread.ThreadTransaction;
import utils.TransactionGenerator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {

    private static final int NUMBER_OF_THREADS = 20;
    private static final int NUMBER_OF_TRANSACTION = 1000;

    public static void main(String[] args) {
        AccountGenerator accountGenerator = new AccountGenerator();
        AccountService accountService = new AccountService();
        TransactionGenerator transactionGenerator = new TransactionGenerator();
        accountGenerator.createRandomAccounts();

        List<Account> accountsList = AccountRepository.getInstance().getAccountList();

        System.out.println("ƒŒŒŒŒ");
        System.out.println(accountsList);

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



        System.out.println("œŒ—À≈≈≈≈≈≈≈≈≈");
        System.out.println(accountsList);
        System.out.println("¬ÒÂ„Ó: " + StaticClass.commonCounter.get());
        System.out.println("ÕÂÛ‰‡˜ÌÓ:" + StaticClass.counterOfFailedOperations.get());
        long sumAfter = accountService.getSumOnAllAccounts(accountsList);
        System.out.println("Overall balance " + sumBefore);
        System.out.println("Overall balance " + sumAfter);




    }
}
