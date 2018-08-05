package top.atstudy.advistory.order.vo.req;

import top.atstudy.advistory.order.dao.dto.OrderLogDTO;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumOrderStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * OrderLog 请求参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class OrderLogReq implements Serializable {


    private Long orderLogId;

    private Long orderId;

    private EnumOrderStatus orderStatus;

    private Long operationId;

    private String operationName;

    private Date operationTime;

    private static final long serialVersionUID = 1L;

    public Long getOrderLogId() {
        return orderLogId;
    }

    public void setOrderLogId(Long orderLogId) {
        this.orderLogId = orderLogId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public EnumOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(EnumOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }


    public static OrderLogDTO convertToDTO(OrderLogReq req) {
        return req.convertToDTO();
    }

    public static List<OrderLogDTO> convertToDTO(List<OrderLogReq> reqs) {
        return reqs.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public OrderLogDTO convertToDTO() {
        return BeanUtils.copyProperties(this, OrderLogDTO.class);
    }
}
