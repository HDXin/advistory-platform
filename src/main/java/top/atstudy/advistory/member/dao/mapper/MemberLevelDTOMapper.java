package top.atstudy.advistory.member.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.advistory.member.dao.dto.MemberLevelDTO;
import top.atstudy.advistory.member.dao.dto.MemberLevelDTOExample;

import java.util.List;

public interface MemberLevelDTOMapper {
    long countByExample(MemberLevelDTOExample example);

    int deleteByExample(MemberLevelDTOExample example);

    int deleteByPrimaryKey(Long memberLevelId);

    int insert(MemberLevelDTO record);

    int insertSelective(MemberLevelDTO record);

    List<MemberLevelDTO> selectByExample(MemberLevelDTOExample example);

    MemberLevelDTO selectByPrimaryKey(Long memberLevelId);

    int updateByExampleSelective(@Param("record") MemberLevelDTO record, @Param("example") MemberLevelDTOExample example);

    int updateByExample(@Param("record") MemberLevelDTO record, @Param("example") MemberLevelDTOExample example);

    int updateByPrimaryKeySelective(MemberLevelDTO record);

    int updateByPrimaryKey(MemberLevelDTO record);
}