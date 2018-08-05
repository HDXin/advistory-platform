package top.atstudy.advistory.order.vo.resp;

import top.atstudy.advistory.order.dao.dto.OrderLogDTO;
import top.atstudy.component.base.BaseSpecFields;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumOrderStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderLog 相应参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class OrderLogResp extends BaseSpecFields implements Serializable {


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


    public static OrderLogResp parseSinglet(OrderLogDTO target) {
        OrderLogResp self = new OrderLogResp();
        return self.parse(target);
    }

    public static List<OrderLogResp> parseList(List<OrderLogDTO> targets) {
        return targets.stream().map(OrderLogResp::parseSinglet).collect(Collectors.toList());
    }

    public OrderLogResp parse(OrderLogDTO target) {
        if(target == null) {
            return null;
        }
        BeanUtils.copyProperties(target, this);
        return this;
    }

    public static OrderLogDTO convertToDTO(OrderLogResp resp) {
        return resp.convertToDTO();
    }

    public static List<OrderLogDTO> convertToDTO(List<OrderLogResp> resps) {
        return resps.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public OrderLogDTO convertToDTO() {
        return BeanUtils.copyProperties(this, OrderLogDTO.class);
    }
}
