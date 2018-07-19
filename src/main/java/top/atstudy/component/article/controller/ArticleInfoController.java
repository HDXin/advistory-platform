package top.atstudy.component.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.article.service.IArticleInfoService;
import top.atstudy.component.article.vo.req.ArticleInfoQuery;
import top.atstudy.component.article.vo.resp.ArticleInfoResp;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicController;
import top.atstudy.component.exception.APIException;

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
@RequestMapping("/article")
public class ArticleInfoController extends BasicController {
    /******* Fields Area *******/

    @Autowired
    private IArticleInfoService articleInfoService;

    /******* Construction Area *******/
    /******* GetSet Area ******/


    @GetMapping("/{id}")
    public ArticleInfoResp get(@PathVariable("id") Long id) {
        ArticleInfoResp target = this.articleInfoService.getById(id);
        return target;
    }

    @GetMapping("")
    public Page<ArticleInfoResp> find(ArticleInfoQuery query) {
        Page<ArticleInfoResp> target = this.articleInfoService.findByQuery(query);
        return target;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) throws APIException {
        this.articleInfoService.remove(id, getSessionUser());
    }

    /******* Method Area *******/


}

