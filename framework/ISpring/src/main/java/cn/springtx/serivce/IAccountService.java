package cn.springtx.serivce;

public interface IAccountService {

    void transfer(int sourceId, int targetId, double money);
}
