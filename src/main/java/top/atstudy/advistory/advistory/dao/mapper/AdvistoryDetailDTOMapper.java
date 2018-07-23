package top.atstudy.advistory.advistory.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryDetailDTO;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryDetailDTOExample;

import java.util.List;

public interface AdvistoryDetailDTOMapper {
    long countByExample(AdvistoryDetailDTOExample example);

    int deleteByExample(AdvistoryDetailDTOExample example);

    int deleteByPrimaryKey(Long advistoryDetailId);

    int insert(AdvistoryDetailDTO record);

    int insertSelective(AdvistoryDetailDTO record);

    List<AdvistoryDetailDTO> selectByExample(AdvistoryDetailDTOExample example);

    AdvistoryDetailDTO selectByPrimaryKey(Long advistoryDetailId);

    int updateByExampleSelective(@Param("record") AdvistoryDetailDTO record, @Param("example") AdvistoryDetailDTOExample example);

    int updateByExample(@Param("record") AdvistoryDetailDTO record, @Param("example") AdvistoryDetailDTOExample example);

    int updateByPrimaryKeySelective(AdvistoryDetailDTO record);

    int updateByPrimaryKey(AdvistoryDetailDTO record);
}