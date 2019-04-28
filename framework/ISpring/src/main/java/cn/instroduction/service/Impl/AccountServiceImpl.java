package cn.instroduction.service.Impl;

import cn.instroduction.dao.IAccountDao;
import cn.instroduction.dao.Impl.AccountDaoImpl;
import cn.instroduction.factory.BeanFactory;
import cn.instroduction.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao = (AccountDaoImpl) BeanFactory.getBean("accountDao");

    @Override
    public void save() {
        accountDao.save();
    }
}
