package spring.tutorial.aop.dao;

import org.springframework.stereotype.Repository;
import spring.tutorial.aop.Account;

import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {
    @Override
    public List<Account> findAccounts() {
        return List.of(new Account("John", "Silver"), new Account("Elliot", "Platinum"));
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        if (tripWire) {
            throw new RuntimeException("Totally unexpected!");
        }

        return findAccounts();
    }

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": Adding an account!");
    }

    @Override
    public void getAccount() {
        System.out.println(getClass() + ": Getting an account!");
    }

    @Override
    public void setAccount() {
        System.out.println(getClass() + ": Setting an account!");
    }
}
