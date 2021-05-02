package com.gupaoedu.vip.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        String valueStr = null;
        if(null != strings){
            valueStr = strings.stream().collect(Collectors.joining(","));
        }
        preparedStatement.setString(i, valueStr);
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String valueStr =  resultSet.getString(s);
        valueStr = (null == s) ? "" : valueStr;
        return Arrays.asList(valueStr.split(","));
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String valueStr =  resultSet.getString(i);
        valueStr = (null == valueStr) ? "" : valueStr;
        return Arrays.asList(valueStr.split(","));
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String valueStr =  callableStatement.getString(i);
        valueStr = (null == valueStr) ? "" : valueStr;
        return Arrays.asList(valueStr.split(","));
    }
}
