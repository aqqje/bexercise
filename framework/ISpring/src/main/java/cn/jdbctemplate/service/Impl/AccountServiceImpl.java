package cn.jdbctemplate.service.Impl;


import cn.jdbctemplate.dao.IAccountDao;
import cn.jdbctemplate.dao.Impl.AccountDaoImpl;
import cn.jdbctemplate.dao.Impl.AccountDaoImpl1;
import cn.jdbctemplate.domain.Account;
import cn.jdbctemplate.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {


    @Autowired
    @Qualifier("accountDao1")
    private IAccountDao accountDao;


    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account findById(int id) {
        return accountDao.findById(id);
    }

    @Override
    public void delete(int id) {
        accountDao.delete(id);
    }

    @Override
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public int findAlldis() {
        return accountDao.findAlldis();
    }
}
