package top.atstudy.component.swiper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumSwiperStatus;
import top.atstudy.component.swiper.dao.ISwiperInfoDao;
import top.atstudy.component.swiper.dao.dto.SwiperInfoDTO;
import top.atstudy.component.swiper.dao.dto.SwiperInfoDTOExample;
import top.atstudy.component.swiper.vo.req.SwiperInfoQuery;
import top.atstudy.component.swiper.vo.req.SwiperInfoReq;
import top.atstudy.component.swiper.vo.resp.SwiperInfoResp;

import java.util.List;

/**
 * ISwiperInfoService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class SwiperInfoServiceImpl implements ISwiperInfoService {
    /******* Fields Area *******/

    private ISwiperInfoDao swiperInfoDao;

    /******* Construction Area *******/
    public SwiperInfoServiceImpl(@Autowired ISwiperInfoDao swiperInfoDao) {
        this.swiperInfoDao = swiperInfoDao;
    }

    @Override
    public SwiperInfoResp getById(Long id) {
        SwiperInfoResp target = null;
        SwiperInfoDTOExample example = new SwiperInfoDTOExample();
        SwiperInfoDTOExample.Criteria criteria = example.createCriteria();
        criteria.andSwiperIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<SwiperInfoDTO> targets = this.swiperInfoDao.listByExample(example);
        SwiperInfoDTO targetDto = this.swiperInfoDao.getByExample(example);
        if (targetDto != null) {
            target = SwiperInfoResp.parseSinglet(targetDto);
        }
        return target;
    }



    @Override
    public Page<SwiperInfoResp> findByQuery(SwiperInfoQuery query) {
        Page<SwiperInfoDTO> targetPage = this.swiperInfoDao.findByQuery(query);
        Page<SwiperInfoResp> page = new Page<>(targetPage);
        page.setItems(SwiperInfoResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<SwiperInfoResp> listByQuery(SwiperInfoQuery query) {
        List<SwiperInfoDTO> targets = this.swiperInfoDao.listByQuery(query);
        return SwiperInfoResp.parseList(targets);
    }

    @Override
    public Long countByQuery(SwiperInfoQuery query) {
        return this.swiperInfoDao.countByQuery(query);
    }

    @Override
    public SwiperInfoResp createAndGet(SwiperInfoReq req, IOperatorAware operator) {
        SwiperInfoDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.swiperInfoDao.createAndGet(target);
        return SwiperInfoResp.parseSinglet(target);
    }

    @Override
    public SwiperInfoResp update(SwiperInfoReq req, IOperatorAware operator) {
        SwiperInfoDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.swiperInfoDao.updateAndGet(target);
        return SwiperInfoResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        SwiperInfoDTO target = this.swiperInfoDao.getById(id);
        target.setOperator(operator, false);
        return this.swiperInfoDao.remove(target);
    }

    @Override
    public Boolean enable(Long swiperId, IOperatorAware operator) {
        SwiperInfoDTO target = new SwiperInfoDTO();
        target.setSwiperId(swiperId);
        target.setEnableStatus(EnumSwiperStatus.ENABLE);
        target.setOperator(operator, false);
        return this.swiperInfoDao.update(target);
    }

    @Override
    public Boolean disable(Long swiperId, IOperatorAware operator) {
        SwiperInfoDTO target = new SwiperInfoDTO();
        target.setSwiperId(swiperId);
        target.setEnableStatus(EnumSwiperStatus.DISABLE);
        target.setOperator(operator, false);
        return this.swiperInfoDao.update(target);
    }

    /******* GetSet Area ******/

    /******* Method Area *******/


}
