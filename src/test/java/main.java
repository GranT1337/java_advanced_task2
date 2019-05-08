import entity.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import repository.AccountRepository;
import service.AccountService;
import service.StaticClass;
import utils.AccountGenerator;
import utils.ThreadTransaction;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(Parameterized.class)
public class main {

    private static final int NUMBER_OF_THREADS = 20;
    private static final int NUMBER_OF_TRANSACTION = 1000;


    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[2000][0];
    }

    @Test
    public void dummy() {
        StaticClass.a = 0;
        AccountGenerator accountGenerator = new AccountGenerator();
        AccountService accountService = new AccountService();
        accountGenerator.createRandomAccounts();

        List<Account> accountsList = AccountRepository.getInstance().getAccountList();

        System.out.println("ДОООО");
        System.out.println(accountsList);

        long sumBefore = accountService.getSumOnAllAccounts(accountsList);


        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        IntStream.range(0,NUMBER_OF_TRANSACTION).forEach(i -> executorService.submit(new ThreadTransaction()));
        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println("ПОСЛЕЕЕЕЕЕЕЕЕ");
        System.out.println(accountsList);
        System.out.println(StaticClass.a);
        long sumAfter = accountService.getSumOnAllAccounts(accountsList);
        System.out.println("Overall balance " + sumBefore);
        System.out.println("Overall balance " + sumAfter);

        Assert.assertEquals(NUMBER_OF_TRANSACTION, StaticClass.a);
        Assert.assertEquals(sumBefore, sumAfter);
    }
}
