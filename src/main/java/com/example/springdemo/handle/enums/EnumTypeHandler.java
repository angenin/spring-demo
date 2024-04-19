package com.example.springdemo.handle.enums;

import com.example.springdemo.enums.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB枚举类型处理器
 * @param <T>
 */
@MappedTypes(BaseEnum.class)
@MappedJdbcTypes(JdbcType.TINYINT)
public class EnumTypeHandler<T extends Enum<T> & BaseEnum> extends BaseTypeHandler<T> {

    private final Class<T> type;

    /**
     * 只能由子类调用
     */
    protected EnumTypeHandler() {
        type = (Class<T>) getClass().getSuperclass();
    }

    /**
     * 由 Mybatis 根据类型动态生成实例
     *
     * @param rawClass
     * @see org.apache.ibatis.type.TypeHandlerRegistry#getInstance(Class, Class)
     */
    public EnumTypeHandler(Class<T> rawClass) {
        this.type = rawClass;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        Object value = parameter.getCode();
        if (jdbcType == null) {
            ps.setObject(i, value);
        } else {
            ps.setObject(i, value, jdbcType.TYPE_CODE);
        }
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return valueOf(rs.getString(columnName));
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return valueOf(rs.getString(columnIndex));
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return valueOf(cs.getString(columnIndex));
    }

    private T valueOf(String s) {
        return s == null ? null : BaseEnum.getEnum(type, s);
    }
}
