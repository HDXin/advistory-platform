package top.atstudy.advistory.order.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.advistory.order.dao.dto.OrderInfoDTO;
import top.atstudy.advistory.order.dao.dto.OrderInfoDTOExample;
import java.util.List;

public interface OrderInfoDTOMapper {
    long countByExample(OrderInfoDTOExample example);

    int deleteByExample(OrderInfoDTOExample example);

    int deleteByPrimaryKey(Long orderId);

    int insert(OrderInfoDTO record);

    int insertSelective(OrderInfoDTO record);

    List<OrderInfoDTO> selectByExample(OrderInfoDTOExample example);

    OrderInfoDTO selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") OrderInfoDTO record, @Param("example") OrderInfoDTOExample example);

    int updateByExample(@Param("record") OrderInfoDTO record, @Param("example") OrderInfoDTOExample example);

    int updateByPrimaryKeySelective(OrderInfoDTO record);

    int updateByPrimaryKey(OrderInfoDTO record);
}