package top.atstudy.component.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.atstudy.component.enums.EnumOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-16
 * Time: 21:47
 */
public abstract class BaseDao<T> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BaseDao() {
    }

    public String buildSortSql(List<SortField> sortFields) {
        if (sortFields == null) {
            sortFields = new ArrayList();
        }

        String orderBySql = "create_time " + EnumOrder.DESC.getCode();
        StringBuffer stringBuffer = new StringBuffer();
        sortFields.forEach((v) -> {
            stringBuffer.append(v.getDBColumn()).append(" ").append(v.getOrder().getCode()).append(",");
        });
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            orderBySql = stringBuffer.toString();
        }

        return orderBySql;
    }
}