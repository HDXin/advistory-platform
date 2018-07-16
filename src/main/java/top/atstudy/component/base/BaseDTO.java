package top.atstudy.component.base;

import top.atstudy.component.enums.EnumDeleted;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-16
 * Time: 21:35
 */
public abstract class BaseDTO extends BaseSpecFields {
    public BaseDTO() {
    }

    public void setOperator(Long userId, String operator, String operationId, boolean created) {
        Date date = new Date();
        if (created) {
            this.setCreateUserId(userId);
            this.setCreateUserName(operator);
            this.setCreateTime(date);
            this.setDeleted(EnumDeleted.NORMAL);
            this.setVersion(Integer.valueOf(0));
            if (this.getDisplayOrder() == null) {
                this.setDisplayOrder(Integer.valueOf(0));
            }
        }

        this.setUpdateUserId(userId);
        this.setUpdateUserName(operator);
        this.setUpdateTime(date);
        this.setRid(operationId);
    }

    public void setOperator(IOperatorAware operator, boolean created) {
        this.setOperator(operator.getOperatorId(), operator.getOperatorName(), operator.getOperationId(), created);
    }

}
