package top.atstudy.advistory.advistory.vo.resp;

import top.atstudy.advistory.advistory.dao.dto.AdvistoryDetailDTO;
import top.atstudy.component.base.BaseSpecFields;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumAdvistoryDetailType;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AdvistoryDetail 相应参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class AdvistoryDetailResp extends BaseSpecFields implements Serializable {


    private Long advistoryDetailId;

    private Long advistoryId;

    private EnumAdvistoryDetailType advistoryDetailType;

    private String content;

    private String background;

    private String link;

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


    public static AdvistoryDetailResp parseSinglet(AdvistoryDetailDTO target) {
        AdvistoryDetailResp self = new AdvistoryDetailResp();
        return self.parse(target);
    }

    public static List<AdvistoryDetailResp> parseList(List<AdvistoryDetailDTO> targets) {
        return targets.stream().map(AdvistoryDetailResp::parseSinglet).collect(Collectors.toList());
    }

    public AdvistoryDetailResp parse(AdvistoryDetailDTO target) {
        if(target == null) {
            return null;
        }
        BeanUtils.copyProperties(target, this);
        return this;
    }

    public static AdvistoryDetailDTO convertToDTO(AdvistoryDetailResp resp) {
        return resp.convertToDTO();
    }

    public static List<AdvistoryDetailDTO> convertToDTO(List<AdvistoryDetailResp> resps) {
        return resps.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public AdvistoryDetailDTO convertToDTO() {
        return BeanUtils.copyProperties(this, AdvistoryDetailDTO.class);
    }
}
