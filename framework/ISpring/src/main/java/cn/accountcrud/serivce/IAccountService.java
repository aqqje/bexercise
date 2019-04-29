package cn.accountcrud.serivce;

import cn.accountcrud.domain.Account;

import java.util.List;

public interface IAccountService {

    List<Account> findAll();

    void save(Account account);

    void delete(int id);

    void update(Account account);
}
