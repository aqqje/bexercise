package cn.transaction.transaction;

import cn.transaction.utils.ConnectionUtil;

/**
 * 事务管理
 */
public class TransationManager {

    private ConnectionUtil connUtil;

    public void setConn(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }

    /*开启事务*/
    public void beginTransaction(){
        try{
            System.out.println("beginTransaction...");
            connUtil.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /*提交事务*/
    public void commit(){
        try{
            connUtil.getThreadConnection().commit();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    /*回滚事务*/
    public void rollback(){
        try{
            connUtil.getThreadConnection().rollback();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /*释放资源*/
    public void release(){
        try{
            connUtil.getThreadConnection().close();
            connUtil.removeConnection();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
