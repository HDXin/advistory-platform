package top.atstudy.advistory.advistory.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryInfoDTO;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryInfoDTOExample;

import java.util.List;

public interface AdvistoryInfoDTOMapper {
    long countByExample(AdvistoryInfoDTOExample example);

    int deleteByExample(AdvistoryInfoDTOExample example);

    int deleteByPrimaryKey(Long advistoryId);

    int insert(AdvistoryInfoDTO record);

    int insertSelective(AdvistoryInfoDTO record);

    List<AdvistoryInfoDTO> selectByExample(AdvistoryInfoDTOExample example);

    AdvistoryInfoDTO selectByPrimaryKey(Long advistoryId);

    int updateByExampleSelective(@Param("record") AdvistoryInfoDTO record, @Param("example") AdvistoryInfoDTOExample example);

    int updateByExample(@Param("record") AdvistoryInfoDTO record, @Param("example") AdvistoryInfoDTOExample example);

    int updateByPrimaryKeySelective(AdvistoryInfoDTO record);

    int updateByPrimaryKey(AdvistoryInfoDTO record);

    int addReadNumber(Long advistoryId);

    int addFavoriteNumber(Long advistoryId);

    int subFavoriteNumber(Long advistoryId);
}