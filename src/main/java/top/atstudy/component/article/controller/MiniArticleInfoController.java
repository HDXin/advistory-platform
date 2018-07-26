package top.atstudy.component.article.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.base.enums.http.BadRequest;
import top.atstudy.component.article.service.IArticleInfoService;
import top.atstudy.component.article.vo.ContentItem;
import top.atstudy.component.article.vo.req.ArticleInfoQuery;
import top.atstudy.component.article.vo.req.ArticleReq;
import top.atstudy.component.article.vo.resp.ArticleInfoResp;
import top.atstudy.component.article.vo.resp.ArticleResp;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicAdminController;
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
@RequestMapping("/api/mini/article")
public class MiniArticleInfoController extends BasicAdminController {
    /******* Fields Area *******/

    @Autowired
    private IArticleInfoService articleInfoService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 获取文章详情
     * @param code
     * @return
     * @throws APIException
     */
    @GetMapping("/code")
    public ArticleResp get(@RequestParam("code") String code) throws APIException {
        return ArticleResp.toArticleResp(articleInfoService.getByCode(code));
    }


}

