package top.atstudy.component.user.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.component.user.dao.dto.AppUserDTO;
import top.atstudy.component.user.dao.dto.AppUserDTOExample;

import java.util.List;

public interface AppUserDTOMapper {
    long countByExample(AppUserDTOExample example);

    int deleteByExample(AppUserDTOExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(AppUserDTO record);

    int insertSelective(AppUserDTO record);

    List<AppUserDTO> selectByExample(AppUserDTOExample example);

    AppUserDTO selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") AppUserDTO record, @Param("example") AppUserDTOExample example);

    int updateByExample(@Param("record") AppUserDTO record, @Param("example") AppUserDTOExample example);

    int updateByPrimaryKeySelective(AppUserDTO record);

    int updateByPrimaryKey(AppUserDTO record);
}