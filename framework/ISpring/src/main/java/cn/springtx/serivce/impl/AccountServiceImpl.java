package cn.springtx.serivce.impl;

import cn.springtx.dao.IAccountDao;
import cn.springtx.domain.Account;
import cn.springtx.serivce.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) // 只读事务类型
public class AccountServiceImpl implements IAccountService {

    @Autowired
    @Qualifier("accountDao")
    private IAccountDao accountDao;

    /**
     * 转帐
     * @param sourceId
     * @param targetId
     * @param money
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void transfer(int sourceId, int targetId, double money) {
        // 获取操作帐号
        Account source = accountDao.findById(sourceId);
        Account target = accountDao.findById(targetId);
        // 减钱
        source.setMoney(source.getMoney() - money);
        // 加钱
        target.setMoney(target.getMoney() + money);
        // 保存
        accountDao.update(source);
        int i = 1 / 0;
        accountDao.update(target);
    }
}
