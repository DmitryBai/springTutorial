package spring.tutorial.aop.dao;

import spring.tutorial.aop.Account;

import java.util.List;

public interface AccountDAO {

    List<Account> findAccounts();

    List<Account> findAccounts(boolean tripWire);

    void addAccount(Account account, boolean vipFlag);

    void getAccount();

    void setAccount();
}
