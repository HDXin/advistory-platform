package top.atstudy.component.article.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.component.article.dao.dto.ArticleInfoDTO;
import top.atstudy.component.article.dao.dto.ArticleInfoDTOExample;

import java.util.List;

public interface ArticleInfoDTOMapper {
    long countByExample(ArticleInfoDTOExample example);

    int deleteByExample(ArticleInfoDTOExample example);

    int deleteByPrimaryKey(Long articleId);

    int insert(ArticleInfoDTO record);

    int insertSelective(ArticleInfoDTO record);

    List<ArticleInfoDTO> selectByExample(ArticleInfoDTOExample example);

    ArticleInfoDTO selectByPrimaryKey(Long articleId);

    int updateByExampleSelective(@Param("record") ArticleInfoDTO record, @Param("example") ArticleInfoDTOExample example);

    int updateByExample(@Param("record") ArticleInfoDTO record, @Param("example") ArticleInfoDTOExample example);

    int updateByPrimaryKeySelective(ArticleInfoDTO record);

    int updateByPrimaryKey(ArticleInfoDTO record);
}