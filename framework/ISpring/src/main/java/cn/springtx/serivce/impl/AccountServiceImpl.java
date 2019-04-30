package cn.springtx.serivce.impl;

import cn.springtx.dao.IAccountDao;
import cn.springtx.domain.Account;
import cn.springtx.serivce.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    /**
     * 转帐
     * @param sourceId
     * @param targetId
     * @param money
     */
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
//        int i = 1 / 0;
        accountDao.update(target);
    }
}
