package top.atstudy.component.user;

import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.user.dao.dto.AdminUserDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-09
 * Time: 17:34
 */
public class AdminSessionUser extends AdminUserDTO implements IOperatorAware{

    @Override
    public Long getOperatorId() {
        return getUserId();
    }

    @Override
    public String getOperatorName() {
        return getUserName();
    }

    @Override
    public String getOperationId() {
        return getUserId().toString();
    }
}
