package spring.tutorial.aop.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {
    @Override
    public boolean addSomething() {
        System.out.println(getClass() + ": Adding an account!");
        return true;
    }
}
