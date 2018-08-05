package top.atstudy.advistory.order.vo.resp;

import top.atstudy.advistory.order.dao.dto.OrderInfoDTO;
import top.atstudy.component.base.BaseSpecFields;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumOrderStatus;
import top.atstudy.component.enums.EnumPaymentStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderInfo 相应参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class OrderInfoResp extends BaseSpecFields implements Serializable {


    private Long orderId;

    private String orderNo;

    private Long amount;

    private EnumOrderStatus orderStatus;

    private Long levelId;

    private String levelName;

    private Integer monthNumber;

    private EnumPaymentStatus paymentStatus;

    private Long prepayUserId;

    private String prepayUserName;

    private Date prepayTime;

    private Long payerId;

    private String payerName;

    private Date paymentTime;

    private static final long serialVersionUID = 1L;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public EnumOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(EnumOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public Integer getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }

    public EnumPaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(EnumPaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Long getPrepayUserId() {
        return prepayUserId;
    }

    public void setPrepayUserId(Long prepayUserId) {
        this.prepayUserId = prepayUserId;
    }

    public String getPrepayUserName() {
        return prepayUserName;
    }

    public void setPrepayUserName(String prepayUserName) {
        this.prepayUserName = prepayUserName == null ? null : prepayUserName.trim();
    }

    public Date getPrepayTime() {
        return prepayTime;
    }

    public void setPrepayTime(Date prepayTime) {
        this.prepayTime = prepayTime;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName == null ? null : payerName.trim();
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }


    public static OrderInfoResp parseSinglet(OrderInfoDTO target) {
        OrderInfoResp self = new OrderInfoResp();
        return self.parse(target);
    }

    public static List<OrderInfoResp> parseList(List<OrderInfoDTO> targets) {
        return targets.stream().map(OrderInfoResp::parseSinglet).collect(Collectors.toList());
    }

    public OrderInfoResp parse(OrderInfoDTO target) {
        if(target == null) {
            return null;
        }
        BeanUtils.copyProperties(target, this);
        return this;
    }

    public static OrderInfoDTO convertToDTO(OrderInfoResp resp) {
        return resp.convertToDTO();
    }

    public static List<OrderInfoDTO> convertToDTO(List<OrderInfoResp> resps) {
        return resps.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public OrderInfoDTO convertToDTO() {
        return BeanUtils.copyProperties(this, OrderInfoDTO.class);
    }
}
