package top.atstudy.component.swiper.service;


import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.swiper.vo.req.SwiperInfoQuery;
import top.atstudy.component.swiper.vo.req.SwiperInfoReq;
import top.atstudy.component.swiper.vo.resp.SwiperInfoResp;
import java.util.List;

/**
 * ISwiperInfoService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface ISwiperInfoService {

    SwiperInfoResp getById(Long id);

    Page<SwiperInfoResp> findByQuery(SwiperInfoQuery query);

    List<SwiperInfoResp> listByQuery(SwiperInfoQuery query);

    Long countByQuery(SwiperInfoQuery query);

    SwiperInfoResp createAndGet(SwiperInfoReq req, IOperatorAware operator);

    SwiperInfoResp update(SwiperInfoReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);

    /**
     * 启用
     * @param swiperId
     * @param operator
     * @return
     */
    Boolean enable(Long swiperId, IOperatorAware operator);

    /**
     * 禁用
     * @param swiperId
     * @param operator
     * @return
     */
    Boolean disable(Long swiperId, IOperatorAware operator);
}

