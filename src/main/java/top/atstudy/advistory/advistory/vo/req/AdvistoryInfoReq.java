package top.atstudy.advistory.advistory.vo.req;

import top.atstudy.advistory.advistory.dao.dto.AdvistoryInfoDTO;
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
 * AdvistoryInfo 请求参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class AdvistoryInfoReq extends AdvistoryInfoDTO implements Serializable {

    private String publishTimeStr;

    private List<AdvistoryDetailReq> details = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    public String getPublishTimeStr() {
        return publishTimeStr;
    }

    public void setPublishTimeStr(String publishTimeStr) {
        this.publishTimeStr = publishTimeStr;
    }

    public List<AdvistoryDetailReq> getDetails() {
        return details;
    }

    public void setDetails(List<AdvistoryDetailReq> details) {
        this.details = details;
    }

    public static AdvistoryInfoDTO convertToDTO(AdvistoryInfoReq req) {
        return req.convertToDTO();
    }

    public static List<AdvistoryInfoDTO> convertToDTO(List<AdvistoryInfoReq> reqs) {
        return reqs.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public AdvistoryInfoDTO convertToDTO() {
        return BeanUtils.copyProperties(this, AdvistoryInfoDTO.class);
    }
}
