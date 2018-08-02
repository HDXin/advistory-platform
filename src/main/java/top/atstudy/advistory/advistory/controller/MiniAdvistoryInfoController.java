package top.atstudy.advistory.advistory.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.advistory.dao.IAdvistoryInfoDao;
import top.atstudy.advistory.advistory.dao.IFavoriteInfoDao;
import top.atstudy.advistory.advistory.dao.dto.FavoriteInfoDTO;
import top.atstudy.advistory.advistory.service.IAdvistoryInfoService;
import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoQuery;
import top.atstudy.advistory.advistory.vo.resp.AdvistoryInfoResp;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicAppController;
import top.atstudy.component.enums.EnumRelationType;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mini/advistoryInfo")
public class MiniAdvistoryInfoController extends BasicAppController {
    /******* Fields Area *******/

    @Autowired
    private IAdvistoryInfoService advistoryInfoService;

    @Autowired
    private IFavoriteInfoDao favoriteInfoDao;

    @Value("${image.domain}")
    private String imageDomain;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 咨询: 详情
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/{id}")
    public AdvistoryInfoResp get(@PathVariable("id") Long id) {
        AdvistoryInfoResp target = this.advistoryInfoService.getById(id);

        //封面域名拼接
        converImage(target);

        //点击量加1
        this.advistoryInfoService.addReadNumber(id);

        //是否已收藏
        FavoriteInfoDTO temp = this.favoriteInfoDao.getByRelationIdAndType(id, EnumRelationType.ADVISTORY, getSessionUser().getUserId(), true);
        if(temp != null && temp.getFavoriteId() != null)
            target.setFavorite(true);

        return target;
    }

    /**
     * 咨询: 列表
     * @param query
     * @return
     */
    @GetMapping("")
    public Page<AdvistoryInfoResp> find(AdvistoryInfoQuery query) {
        Page<AdvistoryInfoResp> target = this.advistoryInfoService.findByQuery(query);

        //封面域名拼接
        target.setItems(target.getItems().stream().map(item -> {
            //封面域名
            item.setCoverImage(imageDomain + item.getCoverImage());

            //是否已收藏
            FavoriteInfoDTO temp = this.favoriteInfoDao.getByRelationIdAndType(item.getAdvistoryId(), EnumRelationType.ADVISTORY, getSessionUser().getUserId(), true);
            if(temp != null && temp.getFavoriteId() != null)
                item.setFavorite(true);

            return item;
        }).collect(Collectors.toList()));

        return target;
    }

    /**
     * 拼接域名
     */
    private void converImage(AdvistoryInfoResp resp){
        if(StringUtils.isNotBlank(resp.getCoverImage())
                && !resp.getCoverImage().startsWith("https")){
            resp.setCoverImage(imageDomain + resp.getCoverImage());
        }
    }



}

