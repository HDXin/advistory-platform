package top.atstudy.advistory.advistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.advistory.service.IFavoriteInfoService;
import top.atstudy.advistory.advistory.vo.req.FavoriteInfoReq;
import top.atstudy.advistory.advistory.vo.resp.FavoriteInfoResp;
import top.atstudy.component.base.controller.BasicAppController;
import top.atstudy.component.exception.APIException;


@RestController
@RequestMapping("/api/mini/favoriteInfo")
public class MiniFavoriteInfoController extends BasicAppController {
    /******* Fields Area *******/

    @Autowired
    private IFavoriteInfoService favoriteInfoService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 收藏
     * @param req
     * @return
     */
    @PostMapping("")
    public FavoriteInfoResp create(@RequestBody FavoriteInfoReq req) throws APIException {
        return this.favoriteInfoService.favorite(req, getSessionUser());
    }

    /**
     * 取消收藏
     * @param req
     * @return
     */
    @PutMapping("/cancel")
    public FavoriteInfoResp cancel(@RequestBody FavoriteInfoReq req) throws APIException {
        return this.favoriteInfoService.cancel(req, getSessionUser());
    }



}

