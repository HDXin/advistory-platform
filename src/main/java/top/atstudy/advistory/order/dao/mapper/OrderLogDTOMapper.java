package top.atstudy.advistory.order.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.advistory.order.dao.dto.OrderLogDTO;
import top.atstudy.advistory.order.dao.dto.OrderLogDTOExample;
import java.util.List;

public interface OrderLogDTOMapper {
    long countByExample(OrderLogDTOExample example);

    int deleteByExample(OrderLogDTOExample example);

    int deleteByPrimaryKey(Long orderLogId);

    int insert(OrderLogDTO record);

    int insertSelective(OrderLogDTO record);

    List<OrderLogDTO> selectByExample(OrderLogDTOExample example);

    OrderLogDTO selectByPrimaryKey(Long orderLogId);

    int updateByExampleSelective(@Param("record") OrderLogDTO record, @Param("example") OrderLogDTOExample example);

    int updateByExample(@Param("record") OrderLogDTO record, @Param("example") OrderLogDTOExample example);

    int updateByPrimaryKeySelective(OrderLogDTO record);

    int updateByPrimaryKey(OrderLogDTO record);
}