package top.atstudy.advistory.member.vo.req;

import top.atstudy.advistory.member.dao.dto.MemberLevelDTO;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumMemberLevel;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


/**
 * MemberLevel 请求参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class MemberLevelReq implements Serializable {


    private Long memberLevelId;

    private EnumMemberLevel level;

    private String description;

    private Long originPrice;

    private Long finalPrice;

    private Integer months;

    private String imageUrl;

    private Integer orderNumber;

    private static final long serialVersionUID = 1L;

    public Long getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(Long memberLevelId) {
        this.memberLevelId = memberLevelId;
    }

    public EnumMemberLevel getLevel() {
        return level;
    }

    public void setLevel(EnumMemberLevel level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Long originPrice) {
        this.originPrice = originPrice;
    }

    public Long getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Long finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }


    public static MemberLevelDTO convertToDTO(MemberLevelReq req) {
        return req.convertToDTO();
    }

    public static List<MemberLevelDTO> convertToDTO(List<MemberLevelReq> reqs) {
        return reqs.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public MemberLevelDTO convertToDTO() {
        return BeanUtils.copyProperties(this, MemberLevelDTO.class);
    }
}
