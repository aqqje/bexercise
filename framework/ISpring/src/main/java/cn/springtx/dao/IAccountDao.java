package cn.springtx.dao;

import cn.springtx.domain.Account;

public interface IAccountDao {

    Account findById(int id);

    boolean update(Account account);
}
