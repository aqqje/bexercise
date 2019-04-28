package cn.annotaion.provider;

import cn.annotaion.domain.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class UserProvider {

    public String proFindAll(){
        return "select * from user";
    }

    public String proFindByUid(Map<String, Object> para){
        /*para:获取值的顺序是根据-Dao接口参数的顺序,也可以是参数定义名
        * "select * from user where id = " + para.get("param1");
        * */
        return "select * from user where id = " + para.get("id");
    }
    public String proFindByCondition(User user){
        StringBuilder sql = new StringBuilder("select * from user where 1=1");
        if(((Integer)user.getId()) != null){
            sql.append(" and id = "+ user.getId());
        }
        if(user.getUsername() != null){
            sql.append(" and username = '"+ user.getUsername()+"'");
        }
        if(user.getSex() != null){
            sql.append(" and sex = '"+ user.getSex()+"'");
        }
        if(user.getBirthday() != null){
            sql.append(" and birthday = '"+ user.getBirthday()+"'");
        }
        if(user.getAddress() != null){
            sql.append(" and address = '"+ user.getAddress()+"'");
        }
        return sql.toString();
    }
}
