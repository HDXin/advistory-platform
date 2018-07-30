package top.atstudy.advistory.advistory.vo.resp;

import top.atstudy.advistory.advistory.dao.dto.FavoriteInfoDTO;
import top.atstudy.component.base.BaseSpecFields;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumFavoriteStatus;
import top.atstudy.component.enums.EnumRelationType;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FavoriteInfo 相应参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class FavoriteInfoResp extends BaseSpecFields implements Serializable {


    private Long favoriteId;

    private Long userId;

    private EnumRelationType relationType;

    private Long relationId;

    private String title;

    private Boolean enable;

    private EnumFavoriteStatus favoriteStatus;

    private static final long serialVersionUID = 1L;

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EnumRelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(EnumRelationType relationType) {
        this.relationType = relationType;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public EnumFavoriteStatus getFavoriteStatus() {
        return favoriteStatus;
    }

    public void setFavoriteStatus(EnumFavoriteStatus favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }


    public static FavoriteInfoResp parseSinglet(FavoriteInfoDTO target) {
        FavoriteInfoResp self = new FavoriteInfoResp();
        return self.parse(target);
    }

    public static List<FavoriteInfoResp> parseList(List<FavoriteInfoDTO> targets) {
        return targets.stream().map(FavoriteInfoResp::parseSinglet).collect(Collectors.toList());
    }

    public FavoriteInfoResp parse(FavoriteInfoDTO target) {
        if(target == null) {
            return null;
        }
        BeanUtils.copyProperties(target, this);
        return this;
    }

    public static FavoriteInfoDTO convertToDTO(FavoriteInfoResp resp) {
        return resp.convertToDTO();
    }

    public static List<FavoriteInfoDTO> convertToDTO(List<FavoriteInfoResp> resps) {
        return resps.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public FavoriteInfoDTO convertToDTO() {
        return BeanUtils.copyProperties(this, FavoriteInfoDTO.class);
    }
}
