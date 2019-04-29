package cn.instroduction.dao.Impl;

import cn.instroduction.dao.IAccountDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository("accountDao1")
public class AccountDaoImpl implements IAccountDao {

    private int i = 1;
    @Override
    public void save() {
        System.out.println("保存了帐号!");
        System.out.println(i);
        i++;
    }
}
