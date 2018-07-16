package top.atstudy.component.user;

import top.atstudy.component.base.IOperatorAware;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-09
 * Time: 17:34
 */
public class SessionUser implements IOperatorAware{

    private Long userId;
    private String userName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Long getOperatorId() {
        return this.userId;
    }

    @Override
    public String getOperatorName() {
        return this.userName;
    }

    @Override
    public String getOperationId() {
        return this.userId.toString();
    }
}
