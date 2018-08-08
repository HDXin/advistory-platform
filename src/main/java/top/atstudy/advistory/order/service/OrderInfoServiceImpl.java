package top.atstudy.advistory.order.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.atstudy.advistory.base.config.LocalPayConfig;
import top.atstudy.advistory.base.enums.http.BadRequest;
import top.atstudy.advistory.member.dao.IMemberLevelDao;
import top.atstudy.advistory.member.dao.dto.MemberLevelDTO;
import top.atstudy.advistory.order.dao.IOrderInfoDao;
import top.atstudy.advistory.order.dao.dto.OrderInfoDTO;
import top.atstudy.advistory.order.dao.dto.OrderInfoDTOExample;
import top.atstudy.advistory.order.vo.req.OrderInfoQuery;
import top.atstudy.advistory.order.vo.req.OrderInfoReq;
import top.atstudy.advistory.order.vo.resp.OrderInfoResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumOrderStatus;
import top.atstudy.component.enums.EnumPaymentStatus;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.AppSessionUser;
import top.atstudy.sdk.payment.wechat.config.PayConfig;
import top.atstudy.sdk.payment.wechat.service.PaymentService;
import top.atstudy.sdk.payment.wechat.vo.UnifiedOrderReq;
import top.atstudy.sdk.payment.wechat.vo.UnifiedOrderResp;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * IOrderInfoService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {
    /******* Fields Area *******/

    private IOrderInfoDao orderInfoDao;

    @Autowired
    private IMemberLevelDao memberLevelDao;


    @Autowired
    private LocalPayConfig localPayConfig;

    /******* Construction Area *******/
    public OrderInfoServiceImpl(@Autowired IOrderInfoDao orderInfoDao) {
        this.orderInfoDao = orderInfoDao;
    }

    @Override
    public OrderInfoResp getById(Long id) {
        OrderInfoResp target = null;
        OrderInfoDTOExample example = new OrderInfoDTOExample();
        OrderInfoDTOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        OrderInfoDTO targetDto = this.orderInfoDao.getByExample(example);
        if (targetDto != null) {
            target = OrderInfoResp.parseSinglet(targetDto);
        }
        return target;
    }



    @Override
    public Page<OrderInfoResp> findByQuery(OrderInfoQuery query) {
        Page<OrderInfoDTO> targetPage = this.orderInfoDao.findByQuery(query);
        Page<OrderInfoResp> page = new Page<>(targetPage);
        page.setItems(OrderInfoResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<OrderInfoResp> listByQuery(OrderInfoQuery query) {
        List<OrderInfoDTO> targets = this.orderInfoDao.listByQuery(query);
        return OrderInfoResp.parseList(targets);
    }

    @Override
    public Long countByQuery(OrderInfoQuery query) {
        return this.orderInfoDao.countByQuery(query);
    }

    /**
     * 用户购买会员
     * @param req
     * @param operator
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderInfoResp createAndGet(OrderInfoReq req, IOperatorAware operator) throws APIException {

        //1.levelId 不能为空
        if(req.getLevelId() == null)
            throw new APIException(BadRequest.LEVEL_ID_IS_NULL);

        //2.判断 levelId 是否有效
        MemberLevelDTO level = this.memberLevelDao.getById(req.getLevelId());
        if(level == null || level.getMemberLevelId() == null)
            throw new APIException(BadRequest.LEVEL_ID_INVALID);

        OrderInfoDTO target = req.convertToDTO();
        //3.会员信息
        target.setAmount(level.getFinalPrice());
        target.setLevelName(level.getDescription());
        target.setMonthNumber(level.getMonths());

        //4.订单状态
        target.setOrderStatus(EnumOrderStatus.NEW_ORDER);
        target.setPaymentStatus(EnumPaymentStatus.NEW_ORDER);

        //5.订单号
        String orderNo = System.currentTimeMillis() + randomNumber(6);
        target.setOrderNo(orderNo);

        target.setOperator(operator, true);
        target = this.orderInfoDao.createAndGet(target);
        return OrderInfoResp.parseSinglet(target);
    }

    @Override
    public OrderInfoResp update(OrderInfoReq req, IOperatorAware operator) {
        OrderInfoDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.orderInfoDao.updateAndGet(target);
        return OrderInfoResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        OrderInfoDTO target = this.orderInfoDao.getById(id);
        target.setOperator(operator, false);
        return this.orderInfoDao.remove(target);
    }

    @Override
    public UnifiedOrderResp prepay(OrderInfoReq req, AppSessionUser sessionUser) throws InvocationTargetException, IllegalAccessException, APIException {

        //1.判断订单号是否有效
        OrderInfoDTO target = this.orderInfoDao.getByOrderNo(req.getOrderNo());
        if(target == null || target.getOrderId() == null)
            throw new APIException(BadRequest.ORDER_INFO_NOT_EXISTS);

        //2.判断订单状态是否合法
        if(target.getOrderStatus() != EnumOrderStatus.NEW_ORDER)
            throw new APIException(BadRequest.ORDER_INFO_STATUS_INVALID);

        //3.统一下单
        UnifiedOrderReq unifiedOrderReq = new UnifiedOrderReq();
        unifiedOrderReq.setOut_trade_no(target.getOrderNo());
        unifiedOrderReq.setTotal_fee(target.getAmount());
        unifiedOrderReq.setOpenid(sessionUser.getOpenid());

        //4.维护配置
        PayConfig payConfig = BeanUtils.copyProperties(localPayConfig, PayConfig.class);

        //5.统一下单
        UnifiedOrderResp resp = PaymentService.getInstance(payConfig).unifiedorder(unifiedOrderReq);

        return resp;
    }

    /******* GetSet Area ******/

    /******* Method Area *******/
    private String randomNumber(int count){

        String randoms = "";
        if(count == 0)
            return randoms;

        for(;count>0;count--){
            randoms += RandomUtils.nextInt(0, 10);
        }

        return randoms;
    }

}
