package thread;

import entity.Transfer;
import service.TransactionService;

public class ThreadTransaction implements Runnable {

    private Transfer transfer;
    private TransactionService transactionService = TransactionService.getInstance();

    public ThreadTransaction(Transfer transfer) {
        this.transfer = transfer;
    }

    @Override
    public void run() {
        try {
            transactionService.transferMoney(transfer);
        } catch (RuntimeException e) {
            System.out.println("lol");
        }

    }
}
