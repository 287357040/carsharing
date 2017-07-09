package com.carsharing.server.util;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 */
public class EmptyStringIfNull implements TypeHandler<String> {

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (columnName.indexOf("time") != -1) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            value = format.format(rs.getTimestamp(columnName));
        }
        return (value == null) ? "" : value;
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        return (rs.getString(columnIndex) == null) ? "" : rs.getString(columnIndex);
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return (cs.getString(columnIndex) == null) ? "" : cs.getString(columnIndex);
    }

    @Override
    public void setParameter(PreparedStatement ps, int arg1, String str, JdbcType jdbcType) {

    }
}
