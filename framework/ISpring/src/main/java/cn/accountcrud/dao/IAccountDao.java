package cn.accountcrud.dao;

import cn.accountcrud.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IAccountDao {

    List<Account> findAll();

    void save(Account account);

    void delete(int id);

    void update(Account account);
}
