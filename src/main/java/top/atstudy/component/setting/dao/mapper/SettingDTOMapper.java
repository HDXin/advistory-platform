package top.atstudy.component.setting.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.component.setting.dao.dto.SettingDTO;
import top.atstudy.component.setting.dao.dto.SettingDTOExample;

import java.util.List;

public interface SettingDTOMapper {
    long countByExample(SettingDTOExample example);

    int deleteByExample(SettingDTOExample example);

    int deleteByPrimaryKey(Long settingId);

    int insert(SettingDTO record);

    int insertSelective(SettingDTO record);

    List<SettingDTO> selectByExample(SettingDTOExample example);

    SettingDTO selectByPrimaryKey(Long settingId);

    int updateByExampleSelective(@Param("record") SettingDTO record, @Param("example") SettingDTOExample example);

    int updateByExample(@Param("record") SettingDTO record, @Param("example") SettingDTOExample example);

    int updateByPrimaryKeySelective(SettingDTO record);

    int updateByPrimaryKey(SettingDTO record);
}