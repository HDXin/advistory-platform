package top.atstudy.component.article.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.base.enums.http.BadRequest;
import top.atstudy.component.article.service.IArticleInfoService;
import top.atstudy.component.article.vo.ContentItem;
import top.atstudy.component.article.vo.req.ArticleInfoQuery;
import top.atstudy.component.article.vo.req.ArticleInfoReq;
import top.atstudy.component.article.vo.req.ArticleReq;
import top.atstudy.component.article.vo.resp.ArticleInfoResp;
import top.atstudy.component.article.vo.resp.ArticleResp;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicController;
import top.atstudy.component.exception.APIException;

import java.util.Comparator;
import java.util.List;

/**
 * smart-mybatis-spring-boot-starter
 * <p>
 * Description :
 * <p>
 * Creator :
 *
 * @author Sudao @ Tim Zhang
 * @email : zhanglong@kuaicto.com or solidsnake2007@gmail.com
 * @date: 2017/11/15
 * @time: 下午1:27
 * =========================================
 * <p>
 * Contributors :
 * Tim Zhang - 2017/11/15 下午1:27
 */
@RestController
@RequestMapping("/admin/article")
public class ArticleInfoController extends BasicController {
    /******* Fields Area *******/

    @Autowired
    private IArticleInfoService articleInfoService;

    /******* Construction Area *******/
    /******* GetSet Area ******/


    /**
     * 新增文章
     * @param req
     * @return
     * @throws APIException
     */
    @PostMapping("")
    public ArticleResp create(@RequestBody ArticleReq req) throws APIException {

        //code 不能为空
        if(StringUtils.isBlank(req.getCode()))
            throw new APIException(BadRequest.ARTICLE_CODE_IS_NULL);

        if(!isValid(null, req.getCode()))
            throw new APIException(BadRequest.ARTICLE_CODE_IS_EXISTS);

        //排序
        itemSort(req.getContentItemList());
        return ArticleResp.toArticleResp(articleInfoService.createAndGet(req.toArticleInfoReq(), getSessionUser()));
    }

    /**
     * 编辑文章信息
     * @param articleId
     * @param req
     * @return
     * @throws APIException
     */
    @PutMapping("/{articleId}")
    public ArticleResp update(@PathVariable("articleId") Long articleId,
                              @RequestBody ArticleReq req) throws APIException {
        //code 不能为空
        if(StringUtils.isBlank(req.getCode()))
            throw new APIException(BadRequest.ARTICLE_CODE_IS_NULL);

        //判断code是否唯一
        if(!isValid(articleId, req.getCode()))
            throw new APIException(BadRequest.ARTICLE_CODE_IS_EXISTS);

        //排序
        itemSort(req.getContentItemList());
        req.setArticleId(articleId);
        return ArticleResp.toArticleResp(articleInfoService.update(req.toArticleInfoReq(), getSessionUser()));
    }

    /**
     * 获取文章详情
     * @param articleId
     * @return
     * @throws APIException
     */
    @GetMapping("/{articleId}")
    public ArticleResp get(@PathVariable("articleId") Long articleId) throws APIException {
        return ArticleResp.toArticleResp(articleInfoService.getById(articleId));
    }

    /**
     * 获取文章列表
     * @param query
     * @return
     */
    @GetMapping("")
    public Page<ArticleInfoResp> find(ArticleInfoQuery query){
        Page<ArticleInfoResp> pageResp = articleInfoService.findByQuery(query);
        return pageResp;
    }

    /**
     * 判断该 code 是否有效
     * @return
     */
    @GetMapping("/code/isValid")
    public boolean isValid(@RequestParam(value = "articleId", required = false) Long articleId,
                           @RequestParam("code") String code) throws APIException {
        ArticleInfoResp articleInfoResp = articleInfoService.getByCode(code);
        if(articleInfoResp == null || articleInfoResp.getArticleId() == null)
            return true;

        //编辑时存在, 即不唯一
        if(articleId == null)
            return false;

        //排除当前编程项
        if(articleId.equals(articleInfoResp.getArticleId()))
            return true;

        return true;
    }

    /**
     * 删除文章
     * @param articleId
     * @throws APIException
     */
    @DeleteMapping("/{articleId}")
    public void remove(@PathVariable("articleId") Long articleId) throws APIException {
        articleInfoService.remove(articleId, getSessionUser());
    }

    /**
     * 排序
     * @param items
     */
    private void itemSort(List<ContentItem> items){

        if(CollectionUtils.isEmpty(items)){
            return ;
        }

        items.sort(new Comparator<ContentItem>() {
            @Override
            public int compare(ContentItem o1, ContentItem o2) {
                return o1.getDisplayOrder().compareTo(o2.getDisplayOrder());
            }
        });
    }



}

