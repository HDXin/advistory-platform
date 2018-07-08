package top.atstudy.advistory.article.dao.mapper;

import top.atstudy.advistory.article.dao.dto.ArticleDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-09
 * Time: 0:42
 */
public interface ArticleDTOMapper {

    ArticleDTO getById(Long articleId);

}
