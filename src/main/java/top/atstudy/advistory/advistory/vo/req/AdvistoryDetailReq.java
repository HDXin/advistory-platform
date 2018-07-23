package top.atstudy.advistory.advistory.vo.req;

import top.atstudy.advistory.advistory.dao.dto.AdvistoryDetailDTO;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumAdvistoryDetailType;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


/**
 * AdvistoryDetail 请求参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class AdvistoryDetailReq implements Serializable {


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


    public static AdvistoryDetailDTO convertToDTO(AdvistoryDetailReq req) {
        return req.convertToDTO();
    }

    public static List<AdvistoryDetailDTO> convertToDTO(List<AdvistoryDetailReq> reqs) {
        return reqs.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public AdvistoryDetailDTO convertToDTO() {
        return BeanUtils.copyProperties(this, AdvistoryDetailDTO.class);
    }
}
