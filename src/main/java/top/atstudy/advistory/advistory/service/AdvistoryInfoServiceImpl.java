package top.atstudy.advistory.advistory.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.atstudy.advistory.advistory.dao.IAdvistoryDetailDao;
import top.atstudy.advistory.advistory.dao.IAdvistoryInfoDao;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryDetailDTO;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryInfoDTO;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryInfoDTOExample;
import top.atstudy.advistory.advistory.vo.req.AdvistoryDetailReq;
import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoQuery;
import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoReq;
import top.atstudy.advistory.advistory.vo.resp.AdvistoryDetailResp;
import top.atstudy.advistory.advistory.vo.resp.AdvistoryInfoResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;

import java.util.ArrayList;
import java.util.List;

/**
 * IAdvistoryInfoService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class AdvistoryInfoServiceImpl implements IAdvistoryInfoService {
    /******* Fields Area *******/

    private IAdvistoryInfoDao advistoryInfoDao;

    @Autowired
    private IAdvistoryDetailDao advistoryDetailDao;

    /******* Construction Area *******/
    public AdvistoryInfoServiceImpl(@Autowired IAdvistoryInfoDao advistoryInfoDao) {
        this.advistoryInfoDao = advistoryInfoDao;
    }

    @Override
    public AdvistoryInfoResp getById(Long id) {
        AdvistoryInfoResp target = null;
        AdvistoryInfoDTOExample example = new AdvistoryInfoDTOExample();
        AdvistoryInfoDTOExample.Criteria criteria = example.createCriteria();
        criteria.andAdvistoryIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        AdvistoryInfoDTO targetDto = this.advistoryInfoDao.getByExample(example);
        if (targetDto != null) {
            target = AdvistoryInfoResp.parseSinglet(targetDto);
        }
        return target;
    }



    @Override
    public Page<AdvistoryInfoResp> findByQuery(AdvistoryInfoQuery query) {
        Page<AdvistoryInfoDTO> targetPage = this.advistoryInfoDao.findByQuery(query);
        Page<AdvistoryInfoResp> page = new Page<>(targetPage);
        page.setItems(AdvistoryInfoResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<AdvistoryInfoResp> listByQuery(AdvistoryInfoQuery query) {
        List<AdvistoryInfoDTO> targets = this.advistoryInfoDao.listByQuery(query);
        return AdvistoryInfoResp.parseList(targets);
    }

    @Override
    public Long countByQuery(AdvistoryInfoQuery query) {
        return this.advistoryInfoDao.countByQuery(query);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvistoryInfoResp createAndGet(AdvistoryInfoReq req, IOperatorAware operator) {
        AdvistoryInfoDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.advistoryInfoDao.createAndGet(target);
        AdvistoryInfoResp resp = new AdvistoryInfoResp();
        if(CollectionUtils.isEmpty(req.getDetails()))
            return resp;

        //维护文章详情
        List<AdvistoryDetailDTO> details = new ArrayList<>();
        for(AdvistoryDetailReq detailReq:req.getDetails()){
            detailReq.setAdvistoryId(target.getAdvistoryId());

            AdvistoryDetailDTO temp = detailReq.convertToDTO();
            temp.setOperator(operator, true);
            temp = this.advistoryDetailDao.createAndGet(temp);

            details.add(temp);
        }
        resp.setDetails(AdvistoryDetailResp.parseList(details));
        return resp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvistoryInfoResp update(AdvistoryInfoReq req, IOperatorAware operator) {
        AdvistoryInfoDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.advistoryInfoDao.updateAndGet(target);
        return AdvistoryInfoResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        AdvistoryInfoDTO target = this.advistoryInfoDao.getById(id);
        target.setOperator(operator, false);
        return this.advistoryInfoDao.remove(target);
    }
    /******* GetSet Area ******/

    /******* Method Area *******/


}
