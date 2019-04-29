package cn.accountcrud.serivce.impl;

import cn.accountcrud.dao.IAccountDao;
import cn.accountcrud.domain.Account;
import cn.accountcrud.serivce.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    public void delete(int id) {
        accountDao.delete(id);
    }

    @Override
    public void update(Account account) {
         accountDao.update(account);
    }
}
