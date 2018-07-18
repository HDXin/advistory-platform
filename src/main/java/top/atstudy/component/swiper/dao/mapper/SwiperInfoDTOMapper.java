package top.atstudy.component.swiper.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.component.swiper.dao.dto.SwiperInfoDTO;
import top.atstudy.component.swiper.dao.dto.SwiperInfoDTOExample;

import java.util.List;

public interface SwiperInfoDTOMapper {
    long countByExample(SwiperInfoDTOExample example);

    int deleteByExample(SwiperInfoDTOExample example);

    int deleteByPrimaryKey(Long swiperId);

    int insert(SwiperInfoDTO record);

    int insertSelective(SwiperInfoDTO record);

    List<SwiperInfoDTO> selectByExample(SwiperInfoDTOExample example);

    SwiperInfoDTO selectByPrimaryKey(Long swiperId);

    int updateByExampleSelective(@Param("record") SwiperInfoDTO record, @Param("example") SwiperInfoDTOExample example);

    int updateByExample(@Param("record") SwiperInfoDTO record, @Param("example") SwiperInfoDTOExample example);

    int updateByPrimaryKeySelective(SwiperInfoDTO record);

    int updateByPrimaryKey(SwiperInfoDTO record);
}