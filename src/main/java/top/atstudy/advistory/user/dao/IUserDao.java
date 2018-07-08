package top.atstudy.advistory.user.dao;

import top.atstudy.advistory.user.dao.dto.UserDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-08
 * Time: 23:30
 */
public interface IUserDao {

    UserDTO getById(Long userId);

}
