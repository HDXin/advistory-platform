package top.atstudy.component.enums;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import top.atstudy.component.enums.base.ICodeEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-17
 * Time: 0:24
 */
public class NumberCodeEnumTypeHandler extends BaseTypeHandler<ICodeEnum> {
    private Class<? extends Enum> type;

    public NumberCodeEnumTypeHandler(Class<? extends Enum> type) {
        this.type = type;
    }

    public void setNonNullParameter(PreparedStatement ps, int i, ICodeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, ((Integer)parameter.getCode()).intValue());
    }

    public ICodeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        ICodeEnum codeEnum = ICodeEnum.codeOf(((Enum[])this.type.getEnumConstants())[0], value);
        return codeEnum;
    }

    public ICodeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        ICodeEnum codeEnum = ICodeEnum.codeOf(((Enum[])this.type.getEnumConstants())[0], value);
        return codeEnum;
    }

    public ICodeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value = cs.getInt(columnIndex);
        ICodeEnum codeEnum = ICodeEnum.codeOf(((Enum[])this.type.getEnumConstants())[0], value);
        return codeEnum;
    }
}
