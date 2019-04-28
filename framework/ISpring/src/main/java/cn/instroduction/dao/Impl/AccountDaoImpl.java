package cn.instroduction.dao.Impl;

import cn.instroduction.dao.IAccountDao;

public class AccountDaoImpl implements IAccountDao {
    @Override
    public void save() {
        System.out.println("保存了帐号!");
    }
}
