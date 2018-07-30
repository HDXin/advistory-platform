package top.atstudy.advistory.advistory.dao.dto;

import top.atstudy.component.base.BaseDTO;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumFavoriteStatus;
import top.atstudy.component.enums.EnumRelationType;
import java.io.Serializable;

public class FavoriteInfoDTO extends BaseDTO implements Serializable {
    private Long favoriteId;

    private Long userId;

    private EnumRelationType relationType;

    private Long relationId;

    private String title;

    private Boolean enable;

    private EnumFavoriteStatus favoriteStatus;

    private EnumDeleted deleted;

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

    public EnumDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(EnumDeleted deleted) {
        this.deleted = deleted;
    }
}