package cn.transaction.dao;

import cn.transaction.domain.Account;

public interface IAccountDao {

    Account findById(int id);

    boolean update(int id, double money);

    void transfer(int sourceId, int targetId, double money);
}
