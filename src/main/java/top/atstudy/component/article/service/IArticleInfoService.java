package top.atstudy.component.article.service;


import top.atstudy.component.article.vo.req.ArticleInfoQuery;
import top.atstudy.component.article.vo.req.ArticleInfoReq;
import top.atstudy.component.article.vo.resp.ArticleInfoResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;

import java.util.List;

/**
 * IArticleInfoService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IArticleInfoService {

    ArticleInfoResp getById(Long id);

    Page<ArticleInfoResp> findByQuery(ArticleInfoQuery query);

    List<ArticleInfoResp> listByQuery(ArticleInfoQuery query);

    Long countByQuery(ArticleInfoQuery query);

    ArticleInfoResp createAndGet(ArticleInfoReq req, IOperatorAware operator);

    ArticleInfoResp update(ArticleInfoReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);


}

