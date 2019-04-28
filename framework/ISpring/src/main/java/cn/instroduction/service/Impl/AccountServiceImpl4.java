package cn.instroduction.service.Impl;

import cn.instroduction.service.IAccountService;

import java.util.*;

public class AccountServiceImpl4 implements IAccountService {

    private String[] arryStr;
    private Set<String> set;
    private List<String> list;
    private Map<String, Object> map;
    private Properties pro;

    @Override
    public void save() {
        System.out.println(arryStr);
        System.out.println(set);
        System.out.println(list);
        System.out.println(map);
        System.out.println(pro);
    }

    public String[] getArryStr() {
        return arryStr;
    }

    public void setArryStr(String[] arryStr) {
        this.arryStr = arryStr;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Properties getPro() {
        return pro;
    }

    public void setPro(Properties pro) {
        this.pro = pro;
    }



}
