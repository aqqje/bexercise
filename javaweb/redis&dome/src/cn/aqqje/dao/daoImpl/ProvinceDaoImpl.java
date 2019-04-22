package cn.aqqje.dao.daoImpl;

import cn.aqqje.dao.ProvinceDao;
import cn.aqqje.domain.Province;
import cn.aqqje.utils.JDBCUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<Province> findAll() {
        return jdbcTemplate.query("select * from province", new BeanPropertyRowMapper<Province>(Province.class));

    }
}
