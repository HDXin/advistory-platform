package top.atstudy.advistory.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.atstudy.advistory.user.dao.dto.UserDTO;
import top.atstudy.advistory.user.dao.mapper.UserDTOMapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-08
 * Time: 23:30
 */
@Component
public class UserDaoImpl implements IUserDao {

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Override
    public UserDTO getById(Long userId) {
        return this.userDTOMapper.getById(userId);
    }
}
