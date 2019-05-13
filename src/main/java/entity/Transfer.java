package entity;

public class Transfer {

    private Account accountOne;
    private Account accountTwo;
    private long amountMoney;

    public Transfer(Account accountOne, Account accountTwo, long amountMoney) {
        this.accountOne = accountOne;
        this.accountTwo = accountTwo;
        this.amountMoney = amountMoney;
    }

    public Account getAccountOne() {
        return accountOne;
    }

    public Account getAccountTwo() {
        return accountTwo;
    }

    public long getAmountMoney() {
        return amountMoney;
    }

}
