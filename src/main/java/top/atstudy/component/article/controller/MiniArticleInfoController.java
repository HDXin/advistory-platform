package top.atstudy.component.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.article.service.IArticleInfoService;
import top.atstudy.component.article.vo.resp.ArticleResp;
import top.atstudy.component.base.controller.BasicAdminController;
import top.atstudy.component.exception.APIException;

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

