import entity.Account;
import generator.AccountGenerator;
import service.AccountService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int NUMBER_OF_THREADS = 20;
    private static final int NUMBER_OF_TRANSACTION = 1000;

    public static void main(String[] args) {
        AccountGenerator accountGenerator = new AccountGenerator();
        AccountService accountService = new AccountService();
        accountGenerator.createRandomAccounts();

        List<Account> accountsList = accountService.readAccountsFromFile();

        System.out.println(accountsList);

        long sumBefore = accountService.getSumOnAllAccounts(accountsList);
        System.out.println("Общий баланс: " + sumBefore);

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);





    }
}
