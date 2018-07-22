package top.atstudy.component.swiper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicController;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.swiper.service.ISwiperInfoService;
import top.atstudy.component.swiper.vo.req.SwiperInfoQuery;
import top.atstudy.component.swiper.vo.req.SwiperInfoReq;
import top.atstudy.component.swiper.vo.resp.SwiperInfoResp;

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
@RequestMapping("/api/admin/swiper")
public class SwiperInfoController extends BasicController {
    /******* Fields Area *******/

    @Autowired
    private ISwiperInfoService swiperInfoService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 创建轮播图
     * @param req
     * @return
     */
    @PostMapping("")
    public SwiperInfoResp create(@RequestBody SwiperInfoReq req){
        return this.swiperInfoService.createAndGet(req, getSessionUser());
    }

    /**
     * 编辑轮播图
     * @param swiperId
     * @param req
     * @return
     */
    @PutMapping("/{swiperId}")
    public SwiperInfoResp update(@PathVariable("swiperId") Long swiperId,
                                 @RequestBody SwiperInfoReq req){
        req.setSwiperId(swiperId);
        return this.swiperInfoService.update(req, getSessionUser());
    }

    /**
     * 启用
     * @param swiperId
     * @return
     */
    @PutMapping("/{swiperId}/enable")
    public Boolean enable(@PathVariable("swiperId") Long swiperId){
        return this.swiperInfoService.enable(swiperId, getSessionUser());
    }

    /**
     * 禁用
     * @param swiperId
     * @return
     */
    @PutMapping("/{swiperId}/disable")
    public Boolean disable(@PathVariable("swiperId") Long swiperId){
        return this.swiperInfoService.disable(swiperId, getSessionUser());
    }

    /**
     * 轮播图详情
     * @param swiperId
     * @return
     */
    @GetMapping("/{swiperId}")
    public SwiperInfoResp get(@PathVariable("swiperId") Long swiperId) {
        SwiperInfoResp target = this.swiperInfoService.getById(swiperId);
        return target;
    }

    /**
     * 轮播列表
     * @param query
     * @return
     */
    @GetMapping("")
    public Page<SwiperInfoResp> find(SwiperInfoQuery query) {
        Page<SwiperInfoResp> target = this.swiperInfoService.findByQuery(query);
        return target;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) throws APIException {
        this.swiperInfoService.remove(id, getSessionUser());
    }

    /******* Method Area *******/


}

