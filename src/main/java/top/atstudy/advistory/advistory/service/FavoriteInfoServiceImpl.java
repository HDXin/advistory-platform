package top.atstudy.advistory.advistory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.atstudy.advistory.advistory.dao.IAdvistoryInfoDao;
import top.atstudy.advistory.advistory.dao.IFavoriteInfoDao;
import top.atstudy.advistory.advistory.dao.dto.FavoriteInfoDTO;
import top.atstudy.advistory.advistory.dao.dto.FavoriteInfoDTOExample;
import top.atstudy.advistory.advistory.vo.req.FavoriteInfoQuery;
import top.atstudy.advistory.advistory.vo.req.FavoriteInfoReq;
import top.atstudy.advistory.advistory.vo.resp.FavoriteInfoResp;
import top.atstudy.advistory.base.enums.http.BadRequest;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumFavoriteStatus;
import top.atstudy.component.enums.EnumRelationType;
import top.atstudy.component.exception.APIException;

import java.util.List;

/**
 * IFavoriteInfoService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class FavoriteInfoServiceImpl implements IFavoriteInfoService {
    /******* Fields Area *******/

    private IFavoriteInfoDao favoriteInfoDao;

    @Autowired
    private IAdvistoryInfoDao advistoryInfoDao;

    /******* Construction Area *******/
    public FavoriteInfoServiceImpl(@Autowired IFavoriteInfoDao favoriteInfoDao) {
        this.favoriteInfoDao = favoriteInfoDao;
    }

    @Override
    public FavoriteInfoResp getById(Long id) {
        FavoriteInfoResp target = null;
        FavoriteInfoDTOExample example = new FavoriteInfoDTOExample();
        FavoriteInfoDTOExample.Criteria criteria = example.createCriteria();
        criteria.andFavoriteIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<FavoriteInfoDTO> targets = this.favoriteInfoDao.listByExample(example);
        FavoriteInfoDTO targetDto = this.favoriteInfoDao.getByExample(example);
        if (targetDto != null) {
            target = FavoriteInfoResp.parseSinglet(targetDto);
        }
        return target;
    }



    @Override
    public Page<FavoriteInfoResp> findByQuery(FavoriteInfoQuery query) {
        Page<FavoriteInfoDTO> targetPage = this.favoriteInfoDao.findByQuery(query);
        Page<FavoriteInfoResp> page = new Page<>(targetPage);
        page.setItems(FavoriteInfoResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<FavoriteInfoResp> listByQuery(FavoriteInfoQuery query) {
        List<FavoriteInfoDTO> targets = this.favoriteInfoDao.listByQuery(query);
        return FavoriteInfoResp.parseList(targets);
    }

    @Override
    public Long countByQuery(FavoriteInfoQuery query) {
        return this.favoriteInfoDao.countByQuery(query);
    }

    @Override
    public FavoriteInfoResp createAndGet(FavoriteInfoReq req, IOperatorAware operator) {
        FavoriteInfoDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.favoriteInfoDao.createAndGet(target);
        return FavoriteInfoResp.parseSinglet(target);
    }

    @Override
    public FavoriteInfoResp update(FavoriteInfoReq req, IOperatorAware operator) {
        FavoriteInfoDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.favoriteInfoDao.updateAndGet(target);
        return FavoriteInfoResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        FavoriteInfoDTO target = this.favoriteInfoDao.getById(id);
        target.setOperator(operator, false);
        return this.favoriteInfoDao.remove(target);
    }

    /**
     * 收藏
     * @param req
     * @param operator
     * @return
     * @throws APIException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public FavoriteInfoResp favorite(FavoriteInfoReq req, IOperatorAware operator) throws APIException {

        //1.业务 ID 不能为空
        if(req.getRelationId() == null)
            throw new APIException(BadRequest.FAVORITE_RELATION_ID_INVALID);

        //2.收藏类型不能为空
        if(req.getRelationType() == null)
            throw new APIException(BadRequest.FAVORITE_RELATION_TYPE_INVALID);

        //3.判断是否已收藏过
        FavoriteInfoDTO temp = this.favoriteInfoDao.getByRelationIdAndType(req.getRelationId(), req.getRelationType(), operator.getOperatorId());

        //4.更新收藏量
        if(req.getRelationType() == EnumRelationType.ADVISTORY){
            this.advistoryInfoDao.addFavoriteNumber(req.getRelationId());
        }

        //5.若已收藏过, 则直接更改状态; 否则新增一条记录;
        FavoriteInfoDTO target = new FavoriteInfoDTO();
        target.setRelationId(req.getRelationId());
        target.setRelationType(req.getRelationType());
        if(temp == null || temp.getFavoriteId() == null){
            target.setEnable(true);
            target.setUserId(operator.getOperatorId());
            target.setFavoriteStatus(EnumFavoriteStatus.ADD_FAVORITE);

            target.setOperator(operator, true);
            return FavoriteInfoResp.parseSinglet(this.favoriteInfoDao.createAndGet(target));
        }else{
            target.setFavoriteId(temp.getFavoriteId());
            target.setEnable(true);
            target.setFavoriteStatus(EnumFavoriteStatus.ADD_FAVORITE);

            target.setOperator(operator, false);
            return FavoriteInfoResp.parseSinglet(this.favoriteInfoDao.updateAndGet(target));
        }
    }

    /**
     * 取消收藏
     * @param req
     * @param operator
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public FavoriteInfoResp cancel(FavoriteInfoReq req, IOperatorAware operator) throws APIException {

        //1.业务 ID 不能为空
        if(req.getRelationId() == null)
            throw new APIException(BadRequest.FAVORITE_RELATION_ID_INVALID);

        //2.收藏类型不能为空
        if(req.getRelationType() == null)
            throw new APIException(BadRequest.FAVORITE_RELATION_TYPE_INVALID);

        //3.判断是否已收藏过
        FavoriteInfoDTO temp = this.favoriteInfoDao.getByRelationIdAndType(req.getRelationId(), req.getRelationType(), operator.getOperatorId());
        if(temp == null || temp.getFavoriteId() == null)
            throw new APIException(BadRequest.FAVORITE_INFO_NOT_EXISTS);

        //4.更新收藏量
        if(req.getRelationType() == EnumRelationType.ADVISTORY){
            this.advistoryInfoDao.subFavoriteNumber(req.getRelationId());
        }

        //5.取消收藏
        FavoriteInfoDTO target = new FavoriteInfoDTO();
        target.setFavoriteId(temp.getFavoriteId());
        target.setEnable(false);
        target.setFavoriteStatus(EnumFavoriteStatus.CANCEL_FAVORITE);

        target.setOperator(operator, false);
        return FavoriteInfoResp.parseSinglet(this.favoriteInfoDao.updateAndGet(target));
    }

    /******* GetSet Area ******/

    /******* Method Area *******/


}
