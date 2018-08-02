package top.atstudy.advistory.advistory.vo.resp;

import top.atstudy.advistory.advistory.dao.dto.AdvistoryInfoDTO;
import top.atstudy.component.base.BaseSpecFields;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumAdvistoryLevel;
import top.atstudy.component.enums.EnumAdvistoryType;
import top.atstudy.component.enums.EnumDeleted;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AdvistoryInfo 相应参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class AdvistoryInfoResp extends AdvistoryInfoDTO implements Serializable {

    private boolean favorite;

    private List<AdvistoryDetailResp> details = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    public List<AdvistoryDetailResp> getDetails() {
        return details;
    }

    public void setDetails(List<AdvistoryDetailResp> details) {
        this.details = details;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public static AdvistoryInfoResp parseSinglet(AdvistoryInfoDTO target) {
        AdvistoryInfoResp self = new AdvistoryInfoResp();
        return self.parse(target);
    }

    public static List<AdvistoryInfoResp> parseList(List<AdvistoryInfoDTO> targets) {
        return targets.stream().map(AdvistoryInfoResp::parseSinglet).collect(Collectors.toList());
    }

    public AdvistoryInfoResp parse(AdvistoryInfoDTO target) {
        if(target == null) {
            return null;
        }
        BeanUtils.copyProperties(target, this);
        return this;
    }

    public static AdvistoryInfoDTO convertToDTO(AdvistoryInfoResp resp) {
        return resp.convertToDTO();
    }

    public static List<AdvistoryInfoDTO> convertToDTO(List<AdvistoryInfoResp> resps) {
        return resps.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public AdvistoryInfoDTO convertToDTO() {
        return BeanUtils.copyProperties(this, AdvistoryInfoDTO.class);
    }
}
