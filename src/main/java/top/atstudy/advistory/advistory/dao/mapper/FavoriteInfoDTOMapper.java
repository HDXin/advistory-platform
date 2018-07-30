package top.atstudy.advistory.advistory.dao.mapper;

import org.apache.ibatis.annotations.Param;
import top.atstudy.advistory.advistory.dao.dto.FavoriteInfoDTO;
import top.atstudy.advistory.advistory.dao.dto.FavoriteInfoDTOExample;

import java.util.List;

public interface FavoriteInfoDTOMapper {
    long countByExample(FavoriteInfoDTOExample example);

    int deleteByExample(FavoriteInfoDTOExample example);

    int deleteByPrimaryKey(Long favoriteId);

    int insert(FavoriteInfoDTO record);

    int insertSelective(FavoriteInfoDTO record);

    List<FavoriteInfoDTO> selectByExample(FavoriteInfoDTOExample example);

    FavoriteInfoDTO selectByPrimaryKey(Long favoriteId);

    int updateByExampleSelective(@Param("record") FavoriteInfoDTO record, @Param("example") FavoriteInfoDTOExample example);

    int updateByExample(@Param("record") FavoriteInfoDTO record, @Param("example") FavoriteInfoDTOExample example);

    int updateByPrimaryKeySelective(FavoriteInfoDTO record);

    int updateByPrimaryKey(FavoriteInfoDTO record);
}