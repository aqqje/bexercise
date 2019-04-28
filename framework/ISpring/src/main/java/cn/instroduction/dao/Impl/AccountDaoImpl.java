package cn.instroduction.dao.Impl;

import cn.instroduction.dao.IAccountDao;

public class AccountDaoImpl implements IAccountDao {

    private int i = 1;
    @Override
    public void save() {
        System.out.println("保存了帐号!");
        System.out.println(i);
        i++;
    }
}
