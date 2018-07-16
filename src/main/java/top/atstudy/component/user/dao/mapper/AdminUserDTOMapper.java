package top.atstudy.component.user.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.component.user.dao.dto.AdminUserDTO;
import top.atstudy.component.user.dao.dto.AdminUserDTOExample;

import java.util.List;

public interface AdminUserDTOMapper {
    long countByExample(AdminUserDTOExample example);

    int deleteByExample(AdminUserDTOExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(AdminUserDTO record);

    int insertSelective(AdminUserDTO record);

    List<AdminUserDTO> selectByExample(AdminUserDTOExample example);

    AdminUserDTO selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") AdminUserDTO record, @Param("example") AdminUserDTOExample example);

    int updateByExample(@Param("record") AdminUserDTO record, @Param("example") AdminUserDTOExample example);

    int updateByPrimaryKeySelective(AdminUserDTO record);

    int updateByPrimaryKey(AdminUserDTO record);
}