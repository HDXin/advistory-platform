package top.atstudy.advistory.article.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.atstudy.advistory.article.dao.dto.ArticleDTO;
import top.atstudy.advistory.article.dao.mapper.ArticleDTOMapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-09
 * Time: 0:50
 */
@Component
public class ArticleDaoImpl implements IArticleDao {

    @Autowired
    private ArticleDTOMapper articleDTOMapper;

    @Override
    public ArticleDTO getById(Long articleId) {
        return this.articleDTOMapper.getById(articleId);
    }
}
