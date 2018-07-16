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
 * Time: 0:25
 */
public class StringCodeEnumTypeHandler extends BaseTypeHandler<ICodeEnum> {
    private Class<? extends Enum> type;

    public StringCodeEnumTypeHandler(Class<? extends Enum> type) {
        this.type = type;
    }

    public void setNonNullParameter(PreparedStatement ps, int i, ICodeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, (String)parameter.getCode());
    }

    public ICodeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        ICodeEnum codeEnum = ICodeEnum.codeOf(((Enum[])this.type.getEnumConstants())[0], value);
        return codeEnum;
    }

    public ICodeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        ICodeEnum codeEnum = ICodeEnum.codeOf(((Enum[])this.type.getEnumConstants())[0], value);
        return codeEnum;
    }

    public ICodeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        ICodeEnum codeEnum = ICodeEnum.codeOf(((Enum[])this.type.getEnumConstants())[0], value);
        return codeEnum;
    }
}
