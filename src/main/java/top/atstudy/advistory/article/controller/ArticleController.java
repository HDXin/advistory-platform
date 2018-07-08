package top.atstudy.advistory.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.atstudy.advistory.article.dao.IArticleDao;
import top.atstudy.advistory.article.dao.dto.ArticleDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-09
 * Time: 0:52
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleDao articleDao;

    @ResponseBody
    @RequestMapping("/{articleId}")
    public ArticleDTO get(@PathVariable("articleId") Long articleId){
        return this.articleDao.getById(articleId);
    }

}
