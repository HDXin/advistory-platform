package top.atstudy.advistory.article.dao;

import top.atstudy.advistory.article.dao.dto.ArticleDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-09
 * Time: 0:49
 */
public interface IArticleDao {

    ArticleDTO getById(Long articleId);

}
