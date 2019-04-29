package cn.transaction.serivce.impl;

import cn.transaction.dao.IAccountDao;
import cn.transaction.serivce.IAccountService;
import cn.transaction.transaction.TransationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;


    @Autowired
    @Qualifier("tx")
    private TransationManager tx;

    @Override
    public void transfer(int sourceId, int targetId, double money) {
        try {
            tx.beginTransaction();
            accountDao.transfer(sourceId, targetId, money);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            tx.release();
        }
    }
}
