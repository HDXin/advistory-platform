package top.atstudy.advistory.order.vo.req;

import top.atstudy.advistory.order.dao.dto.OrderInfoDTO;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumOrderStatus;
import top.atstudy.component.enums.EnumPaymentStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * OrderInfo 请求参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class OrderInfoReq implements Serializable {


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


    public static OrderInfoDTO convertToDTO(OrderInfoReq req) {
        return req.convertToDTO();
    }

    public static List<OrderInfoDTO> convertToDTO(List<OrderInfoReq> reqs) {
        return reqs.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public OrderInfoDTO convertToDTO() {
        return BeanUtils.copyProperties(this, OrderInfoDTO.class);
    }
}
