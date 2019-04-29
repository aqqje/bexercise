package cn.transaction.serivce;

import cn.transaction.domain.Account;

import java.util.List;

public interface IAccountService {

    void transfer(int sourceId, int targetId, double money);
}
