package thread;

import entity.Transfer;
import exception.NotEnoughMoneyException;
import org.apache.log4j.Logger;
import service.TransactionService;

public class ThreadTransaction implements Runnable {

    private Transfer transfer;
    private TransactionService transactionService = TransactionService.getInstance();
    private static Logger logger = Logger.getLogger(ThreadTransaction.class);

    public ThreadTransaction(Transfer transfer) {
        this.transfer = transfer;
    }

    @Override
    public void run() {
        try {
            transactionService.transferMoney(transfer);
        } catch (NotEnoughMoneyException e) {
            logger.error(e + "\n");
        }

    }
}
