package utils;

public class ThreadTransaction implements Runnable {

    @Override
    public void run() {
        try {
            new TransactionGenerator().getPairAccount();
        } catch (RuntimeException e) {
            System.out.println("lol");
        }

    }
}
