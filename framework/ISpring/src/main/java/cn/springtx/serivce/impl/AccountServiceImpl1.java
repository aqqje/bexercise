package cn.springtx.serivce.impl;

import cn.springtx.dao.IAccountDao;
import cn.springtx.domain.Account;
import cn.springtx.serivce.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Service("accountService1")
public class AccountServiceImpl1 implements IAccountService {

    @Autowired
    @Qualifier("accountDao")
    private IAccountDao accountDao;

    @Autowired
    @Qualifier("tt")
    private TransactionTemplate tt;

    /**
     * 转帐
     * @param sourceId
     * @param targetId
     * @param money
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void transfer(int sourceId, int targetId, double money) {

        /*
            spring-tx:编程式事务
        */
        tt.execute(new TransactionCallback<Object>() {
            @Override
            public Account doInTransaction(TransactionStatus transactionStatus) {
                // 获取操作帐号
                Account source = accountDao.findById(sourceId);
                Account target = accountDao.findById(targetId);
                // 减钱
                source.setMoney(source.getMoney() - money);
                // 加钱
                target.setMoney(target.getMoney() + money);
                // 保存
                accountDao.update(source);
//                int i = 1 / 0;
                accountDao.update(target);
                return null;
            }
        });

    }
}
