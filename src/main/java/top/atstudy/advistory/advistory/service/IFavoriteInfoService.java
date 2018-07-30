package top.atstudy.advistory.advistory.service;

import top.atstudy.advistory.advistory.vo.req.FavoriteInfoQuery;
import top.atstudy.advistory.advistory.vo.req.FavoriteInfoReq;
import top.atstudy.advistory.advistory.vo.resp.FavoriteInfoResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.AppSessionUser;

import java.util.List;

/**
 * IFavoriteInfoService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IFavoriteInfoService {

    FavoriteInfoResp getById(Long id);

    Page<FavoriteInfoResp> findByQuery(FavoriteInfoQuery query);

    List<FavoriteInfoResp> listByQuery(FavoriteInfoQuery query);

    Long countByQuery(FavoriteInfoQuery query);

    FavoriteInfoResp createAndGet(FavoriteInfoReq req, IOperatorAware operator);

    FavoriteInfoResp update(FavoriteInfoReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);

    /**
     * 收藏
     * @param req
     * @param operator
     * @return
     */
    FavoriteInfoResp favorite(FavoriteInfoReq req, IOperatorAware operator) throws APIException;

    /**
     * 取消收藏
     * @param req
     * @param operator
     * @return
     */
    FavoriteInfoResp cancel(FavoriteInfoReq req, IOperatorAware operator) throws APIException;
}

