package top.atstudy.advistory.order.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.atstudy.advistory.base.config.LocalPayConfig;
import top.atstudy.advistory.base.enums.http.BadRequest;
import top.atstudy.advistory.member.dao.IMemberLevelDao;
import top.atstudy.advistory.member.dao.dto.MemberLevelDTO;
import top.atstudy.advistory.order.dao.IOrderInfoDao;
import top.atstudy.advistory.order.dao.IOrderLogDao;
import top.atstudy.advistory.order.dao.dto.OrderInfoDTO;
import top.atstudy.advistory.order.dao.dto.OrderInfoDTOExample;
import top.atstudy.advistory.order.dao.dto.OrderLogDTO;
import top.atstudy.advistory.order.vo.req.OrderInfoQuery;
import top.atstudy.advistory.order.vo.req.OrderInfoReq;
import top.atstudy.advistory.order.vo.resp.OrderInfoResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.*;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.AppSessionUser;
import top.atstudy.component.user.dao.IAppUserDao;
import top.atstudy.component.user.dao.dto.AppUserDTO;
import top.atstudy.sdk.payment.wechat.config.PayConfig;
import top.atstudy.sdk.payment.wechat.service.PaymentService;
import top.atstudy.sdk.payment.wechat.vo.PayNotifyReq;
import top.atstudy.sdk.payment.wechat.vo.PayNotifyResp;
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

    @Autowired
    private IAppUserDao appUserDao;

    @Autowired
    private IOrderLogDao orderLogDao;

    private static final String DEFAULT_BODY = "用户购买会员";

    @Value("${pay.config.ip}")
    private String ip;

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
    public OrderInfoResp getByOrderNo(String orderNo) {
        return OrderInfoResp.parseSinglet(this.orderInfoDao.getByOrderNo(orderNo));
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
        unifiedOrderReq.setBody(DEFAULT_BODY);
        unifiedOrderReq.setSpbill_create_ip(this.ip);

        //4.维护配置
        PayConfig payConfig = BeanUtils.copyProperties(localPayConfig, PayConfig.class);

        //5.统一下单
        UnifiedOrderResp resp = PaymentService.getInstance(payConfig).unifiedorder(unifiedOrderReq);

        //6.订单状态更改为预支付
        if("SUCCESS".equals(resp.getResult_code())
                && "SUCCESS".equals(resp.getReturn_code())){
            OrderInfoDTO order = new OrderInfoDTO();
            order.setOrderStatus(EnumOrderStatus.PREPAY);
            order.setPrepayUserId(sessionUser.getUserId());
            order.setPrepayUserName(sessionUser.getUserName());
            order.setPrepayTime(new Date());

            order.setOperator(sessionUser, false);
            this.orderInfoDao.update(order);
        }

        //7.订单日志 PS: 用户排错处理
        OrderLogDTO orderLog = new OrderLogDTO();
        orderLog.setOrderId(target.getOrderId());
        orderLog.setOrderNo(target.getOrderNo());
        orderLog.setOrderStatus(EnumOrderStatus.PREPAY);
        orderLog.setContent(JSONObject.toJSONString(resp));
        orderLog.setOperator(sessionUser, true);
        this.orderLogDao.create(orderLog);

        return resp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PayNotifyResp callback(PayNotifyReq req, IOperatorAware operator) throws APIException {
        //1.订单日志
        OrderLogDTO orderLog = new OrderLogDTO();
        orderLog.setOrderNo(req.getOut_trade_no());
        orderLog.setContent(JSONObject.toJSONString(req));
        orderLog.setOperator(operator, true);
        if("SUCCESS".equals(req.getResult_code())
                && "SUCCESS".equals(req.getReturn_code()))
            orderLog.setOrderStatus(EnumOrderStatus.PAID);
        this.orderLogDao.create(orderLog);

        //2.微信回调处理
        PayNotifyResp resp = new PayNotifyResp();
        if("SUCCESS".equals(req.getReturn_code())
                && "SUCCESS".equals(req.getResult_code())){
            resp.setReturn_code("SUCCESS");
            resp.setResult_code("OK");
            orderLog.setOrderStatus(EnumOrderStatus.PAID);

            //获取指定订单
            OrderInfoDTO order = orderInfoDao.getByOrderNo(req.getOut_trade_no());
            if(order == null || order.getOrderId() == null)
                return resp;

            //更改订单状态为已支付
            OrderInfoDTO temp = new OrderInfoDTO();
            temp.setOrderId(order.getOrderId());

            temp.setOrderStatus(EnumOrderStatus.PAID);
            temp.setPayerId(order.getPrepayUserId());
            temp.setPayerName(order.getPrepayUserName());
            temp.setPaymentTime(new Date());

            temp.setOperator(operator, false);
            this.orderInfoDao.update(temp);

            //更改当前用户为对应的会员级别
            MemberLevelDTO level = this.memberLevelDao.getById(order.getLevelId());
            if(level == null || level.getMemberLevelId() == null)
                return resp;

            AppUserDTO user = new AppUserDTO();
            user.setUserId(order.getPrepayUserId());
            user.setUserType(EnumUserType.valueOf(level.getLevel().getCode()));
            user.setOperator(operator, false);
            this.appUserDao.update(user);
        }

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
