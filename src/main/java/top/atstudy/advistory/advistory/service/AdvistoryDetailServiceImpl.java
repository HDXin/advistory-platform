package top.atstudy.advistory.advistory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.advistory.advistory.dao.IAdvistoryDetailDao;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryDetailDTO;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryDetailDTOExample;
import top.atstudy.advistory.advistory.vo.req.AdvistoryDetailQuery;
import top.atstudy.advistory.advistory.vo.req.AdvistoryDetailReq;
import top.atstudy.advistory.advistory.vo.resp.AdvistoryDetailResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;

import java.util.List;

/**
 * IAdvistoryDetailService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class AdvistoryDetailServiceImpl implements IAdvistoryDetailService {
    /******* Fields Area *******/

    private IAdvistoryDetailDao advistoryDetailDao;

    /******* Construction Area *******/
    public AdvistoryDetailServiceImpl(@Autowired IAdvistoryDetailDao advistoryDetailDao) {
        this.advistoryDetailDao = advistoryDetailDao;
    }

    @Override
    public AdvistoryDetailResp getById(Long id) {
        AdvistoryDetailResp target = null;
        AdvistoryDetailDTOExample example = new AdvistoryDetailDTOExample();
        AdvistoryDetailDTOExample.Criteria criteria = example.createCriteria();
        criteria.andAdvistoryDetailIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<AdvistoryDetailDTO> targets = this.advistoryDetailDao.listByExample(example);
        AdvistoryDetailDTO targetDto = this.advistoryDetailDao.getByExample(example);
        if (targetDto != null) {
            target = AdvistoryDetailResp.parseSinglet(targetDto);
        }
        return target;
    }



    @Override
    public Page<AdvistoryDetailResp> findByQuery(AdvistoryDetailQuery query) {
        Page<AdvistoryDetailDTO> targetPage = this.advistoryDetailDao.findByQuery(query);
        Page<AdvistoryDetailResp> page = new Page<>(targetPage);
        page.setItems(AdvistoryDetailResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<AdvistoryDetailResp> listByQuery(AdvistoryDetailQuery query) {
        List<AdvistoryDetailDTO> targets = this.advistoryDetailDao.listByQuery(query);
        return AdvistoryDetailResp.parseList(targets);
    }

    @Override
    public Long countByQuery(AdvistoryDetailQuery query) {
        return this.advistoryDetailDao.countByQuery(query);
    }

    @Override
    public AdvistoryDetailResp createAndGet(AdvistoryDetailReq req, IOperatorAware operator) {
        AdvistoryDetailDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.advistoryDetailDao.createAndGet(target);
        return AdvistoryDetailResp.parseSinglet(target);
    }

    @Override
    public AdvistoryDetailResp update(AdvistoryDetailReq req, IOperatorAware operator) {
        AdvistoryDetailDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.advistoryDetailDao.updateAndGet(target);
        return AdvistoryDetailResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        AdvistoryDetailDTO target = this.advistoryDetailDao.getById(id);
        target.setOperator(operator, false);
        return this.advistoryDetailDao.remove(target);
    }
    /******* GetSet Area ******/

    /******* Method Area *******/


}
