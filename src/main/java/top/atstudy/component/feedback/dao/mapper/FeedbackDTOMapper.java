package top.atstudy.component.feedback.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.component.feedback.dao.dto.FeedbackDTO;
import top.atstudy.component.feedback.dao.dto.FeedbackDTOExample;

import java.util.List;

public interface FeedbackDTOMapper {
    long countByExample(FeedbackDTOExample example);

    int deleteByExample(FeedbackDTOExample example);

    int deleteByPrimaryKey(Long feedbackId);

    int insert(FeedbackDTO record);

    int insertSelective(FeedbackDTO record);

    List<FeedbackDTO> selectByExample(FeedbackDTOExample example);

    FeedbackDTO selectByPrimaryKey(Long feedbackId);

    int updateByExampleSelective(@Param("record") FeedbackDTO record, @Param("example") FeedbackDTOExample example);

    int updateByExample(@Param("record") FeedbackDTO record, @Param("example") FeedbackDTOExample example);

    int updateByPrimaryKeySelective(FeedbackDTO record);

    int updateByPrimaryKey(FeedbackDTO record);
}