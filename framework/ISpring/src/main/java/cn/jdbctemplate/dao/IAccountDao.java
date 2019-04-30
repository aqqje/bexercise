package cn.jdbctemplate.dao;

import cn.jdbctemplate.domain.Account;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();

    Account findById(int id);

    void delete(int id);

    void save(Account account);

    void update(Account account);

    int findAlldis();
}
