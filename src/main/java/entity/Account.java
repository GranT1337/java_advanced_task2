package entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Serializable {

    private long id;
    private String name;
    private long balance;
    private ReentrantLock lock = new ReentrantLock();

    public Account(long id, String name, long balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public ReentrantLock getLock() {
        return lock;
    }


    @Override
    public String toString() {
        return "entity.Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}' + "\n";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                balance == account.balance &&
                Objects.equals(name, account.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance);
    }
}
