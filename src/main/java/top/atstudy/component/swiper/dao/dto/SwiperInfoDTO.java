package top.atstudy.component.swiper.dao.dto;

import top.atstudy.component.base.BaseDTO;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumSwiperOpenType;
import top.atstudy.component.enums.EnumSwiperStatus;
import top.atstudy.component.enums.EnumSwiperType;

import java.io.Serializable;
import java.util.Date;

public class SwiperInfoDTO extends BaseDTO implements Serializable {
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

    private EnumDeleted deleted;

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

    public EnumDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(EnumDeleted deleted) {
        this.deleted = deleted;
    }
}