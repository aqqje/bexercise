package cn.springaop.dao.Impl;

import cn.springaop.dao.IAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Override
    public void save() {
        System.out.println("save........");
    }
}
