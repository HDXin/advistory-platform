package top.atstudy.advistory.advistory.dao.dto;

import top.atstudy.component.base.BaseDTO;
import top.atstudy.component.enums.EnumAdvistoryDetailType;
import top.atstudy.component.enums.EnumDeleted;
import java.io.Serializable;

public class AdvistoryDetailDTO extends BaseDTO implements Serializable {
    private Long advistoryDetailId;

    private Long advistoryId;

    private EnumAdvistoryDetailType advistoryDetailType;

    private String content;

    private String background;

    private String link;

    private EnumDeleted deleted;

    private static final long serialVersionUID = 1L;

    public Long getAdvistoryDetailId() {
        return advistoryDetailId;
    }

    public void setAdvistoryDetailId(Long advistoryDetailId) {
        this.advistoryDetailId = advistoryDetailId;
    }

    public Long getAdvistoryId() {
        return advistoryId;
    }

    public void setAdvistoryId(Long advistoryId) {
        this.advistoryId = advistoryId;
    }

    public EnumAdvistoryDetailType getAdvistoryDetailType() {
        return advistoryDetailType;
    }

    public void setAdvistoryDetailType(EnumAdvistoryDetailType advistoryDetailType) {
        this.advistoryDetailType = advistoryDetailType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background == null ? null : background.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public EnumDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(EnumDeleted deleted) {
        this.deleted = deleted;
    }
}