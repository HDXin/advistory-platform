package top.atstudy.component.swiper.vo.resp;

import top.atstudy.component.base.BaseSpecFields;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumSwiperOpenType;
import top.atstudy.component.enums.EnumSwiperStatus;
import top.atstudy.component.enums.EnumSwiperType;
import top.atstudy.component.swiper.dao.dto.SwiperInfoDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SwiperInfo 相应参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class SwiperInfoResp extends BaseSpecFields implements Serializable {


    private Long swiperId;

    private String title;

    private String description;

    private String image;

    private String link;

    private EnumSwiperStatus enableStatus;

    private EnumSwiperType type;

    private EnumSwiperOpenType openType;

    private Date beginTime;

    private Date endTime;

    private static final long serialVersionUID = 1L;

    public Long getSwiperId() {
        return swiperId;
    }

    public void setSwiperId(Long swiperId) {
        this.swiperId = swiperId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public EnumSwiperStatus getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(EnumSwiperStatus enableStatus) {
        this.enableStatus = enableStatus;
    }

    public EnumSwiperType getType() {
        return type;
    }

    public void setType(EnumSwiperType type) {
        this.type = type;
    }

    public EnumSwiperOpenType getOpenType() {
        return openType;
    }

    public void setOpenType(EnumSwiperOpenType openType) {
        this.openType = openType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public static SwiperInfoResp parseSinglet(SwiperInfoDTO target) {
        SwiperInfoResp self = new SwiperInfoResp();
        return self.parse(target);
    }

    public static List<SwiperInfoResp> parseList(List<SwiperInfoDTO> targets) {
        return targets.stream().map(SwiperInfoResp::parseSinglet).collect(Collectors.toList());
    }

    public SwiperInfoResp parse(SwiperInfoDTO target) {
        if(target == null) {
            return null;
        }
        BeanUtils.copyProperties(target, this);
        return this;
    }

    public static SwiperInfoDTO convertToDTO(SwiperInfoResp resp) {
        return resp.convertToDTO();
    }

    public static List<SwiperInfoDTO> convertToDTO(List<SwiperInfoResp> resps) {
        return resps.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public SwiperInfoDTO convertToDTO() {
        return BeanUtils.copyProperties(this, SwiperInfoDTO.class);
    }
}
