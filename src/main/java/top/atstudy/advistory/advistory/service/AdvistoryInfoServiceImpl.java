package top.atstudy.advistory.advistory.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

            //获取资讯详情
            List<AdvistoryDetailDTO> details = this.advistoryDetailDao.getByAdvistoryId(id);
            target.setDetails(AdvistoryDetailResp.parseList(details));

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
    public AdvistoryInfoResp createAndGet(AdvistoryInfoReq req, IOperatorAware operator) throws ParseException {

        AdvistoryInfoDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        if(StringUtils.isNotBlank(req.getPublishTimeStr())){
            updatePublishTime(req.getPublishTimeStr(), target, operator);
        }

        target = this.advistoryInfoDao.createAndGet(target);
        AdvistoryInfoResp resp = AdvistoryInfoResp.parseSinglet(target);
        if(CollectionUtils.isEmpty(req.getDetails()))
            return resp;

        //维护文章详情
        resp.setDetails(saveDetails(target.getAdvistoryId(), req.getDetails(), operator));
        return resp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvistoryInfoResp update(AdvistoryInfoReq req, IOperatorAware operator) throws ParseException {
        //1.删除当前文章的详情
        this.advistoryDetailDao.deleteByAdvistoryId(req.getAdvistoryId());

        //2.保存文章主信息
        AdvistoryInfoDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        if(StringUtils.isNotBlank(req.getPublishTimeStr())){
            updatePublishTime(req.getPublishTimeStr(), target, operator);
        }

        target = this.advistoryInfoDao.updateAndGet(target);
        AdvistoryInfoResp resp = AdvistoryInfoResp.parseSinglet(target);
        if(CollectionUtils.isEmpty(req.getDetails()))
            return resp;

        //3.编辑文章详细信息
        resp.setDetails(saveDetails(target.getAdvistoryId(), req.getDetails(), operator));

        return resp;
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        AdvistoryInfoDTO target = this.advistoryInfoDao.getById(id);
        target.setOperator(operator, false);
        return this.advistoryInfoDao.remove(target);
    }
    /******* GetSet Area ******/

    /******* Method Area *******/
    private List<AdvistoryDetailResp> saveDetails(Long advistoryId, List<AdvistoryDetailReq> detailReqs, IOperatorAware operator){
        List<AdvistoryDetailDTO> details = new ArrayList<>();
        for(AdvistoryDetailReq detailReq:detailReqs){
            detailReq.setAdvistoryId(advistoryId);

            AdvistoryDetailDTO temp = detailReq.convertToDTO();
            temp.setOperator(operator, true);
            temp = this.advistoryDetailDao.createAndGet(temp);

            details.add(temp);
        }
        return AdvistoryDetailResp.parseList(details);
    }

    /**
     * 维护发布人信息
     * @param publishTimeStr
     * @param target
     * @param operator
     */
    private void updatePublishTime(String publishTimeStr, AdvistoryInfoDTO target, IOperatorAware operator) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        target.setPublishTime(sdf.parse(publishTimeStr));

        target.setPublishUserId(operator.getOperatorId());
        target.setPublishUserName(operator.getOperatorName());
        target.setPublishOperationTime(new Date());
    }

}
