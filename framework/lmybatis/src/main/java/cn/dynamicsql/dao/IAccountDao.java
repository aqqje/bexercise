package cn.dynamicsql.dao;

import cn.dynamicsql.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有帐号
     * @return
     */
    List<Account> findAll();

    List<Account> findByUid(Integer uid);

}
