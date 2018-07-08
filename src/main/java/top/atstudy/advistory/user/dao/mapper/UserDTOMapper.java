package top.atstudy.advistory.user.dao.mapper;

import top.atstudy.advistory.user.dao.dto.UserDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-08
 * Time: 23:19
 */
public interface UserDTOMapper {

    UserDTO getById(Long userId);

}
