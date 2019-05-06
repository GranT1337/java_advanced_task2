import entity.Account;
import service.TransactionService;
import utils.AccountGenerator;
import repository.AccountRepository;
import service.AccountService;
import utils.TransactionGenerator;

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

        List<Account> accountsList = AccountRepository.getInstance().getAccountList();

        System.out.println("днннн");
        System.out.println(accountsList);

        long sumBefore = accountService.getSumOnAllAccounts(accountsList);
        System.out.println("Overall balance " + sumBefore);

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        TransactionGenerator transactionGenerator = new TransactionGenerator();

        TransactionService transactionService = new TransactionService();

        transactionService.transfer();

        System.out.println("онякеееееееее");
        System.out.println(accountsList);



    }
}
