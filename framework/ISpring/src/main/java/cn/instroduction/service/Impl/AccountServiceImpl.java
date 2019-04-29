package cn.instroduction.service.Impl;

import cn.instroduction.dao.IAccountDao;
import cn.instroduction.dao.Impl.AccountDaoImpl;
import cn.instroduction.factory.BeanFactory;
import cn.instroduction.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("accountService")
/*singleton*/
@Scope("prototype")
public class AccountServiceImpl implements IAccountService {

//    @Autowired
//    @Qualifier("accountDao1")
    @Resource(name = "accountDao1")
    private IAccountDao accountDao;

    @Override
    public void save() {
        accountDao.save();
    }
}
