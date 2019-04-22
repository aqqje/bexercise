package cn.aqqje.service.serviceImpl;

import cn.aqqje.dao.ProvinceDao;
import cn.aqqje.dao.daoImpl.ProvinceDaoImpl;
import cn.aqqje.domain.Province;
import cn.aqqje.service.ProvinceService;
import cn.aqqje.utils.JedisPoolUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao dao = new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    @Override
    public String findAllJson() {
        Jedis jedis = JedisPoolUtil.getJedis().getResource();
        String provincesJson = jedis.get("provincesJson");
        if(provincesJson == null || provincesJson.length() == 0){
            System.out.println("不存在, 查询数据库.....");
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                provincesJson = objectMapper.writeValueAsString(dao.findAll());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("provincesJson", provincesJson);
            jedis.close();
        }else{
            System.out.println("存在, 查询缓存.....");
        }
        return provincesJson;
    }
}
