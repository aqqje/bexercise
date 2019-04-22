package cn.aqqje.service.impl;

import cn.aqqje.dao.UserDao;
import cn.aqqje.dao.impl.UserDaoImpl;
import cn.aqqje.domain.BeanPage;
import cn.aqqje.domain.User;
import cn.aqqje.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public User login(User loginUser) {
        return dao.findUserByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        dao.deleteUserById(id);
    }

    @Override
    public User findOneById(int id) {
        return dao.findOneById(id);
    }

    @Override
    public void updateUser(User updateUser) {
        dao.updateUser(updateUser);
    }

    @Override
    public void deleteUserByIds(String[] ids) {
        for (String id:ids) {
            dao.deleteUserById(Integer.parseInt(id));
        }
    }


    @Override
    public BeanPage beanPage(int currentPage, int rows, Map<String, String[]> condation) {
        BeanPage<User> pb = new BeanPage<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int totalCount = dao.totalCount(condation);
        pb.setTotalCount(totalCount);
        int totalPage = (totalCount % rows == 0) ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);
        int start = (currentPage - 1)  * rows;
        List<User> list = dao.beanPageList(start, rows,condation);
        pb.setList(list);
        return pb;
    }
}
