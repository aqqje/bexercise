package cn.springaop.service.Impl;


import cn.springaop.dao.IAccountDao;
import cn.springaop.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public void save() {
        accountDao.save();
    }
}
