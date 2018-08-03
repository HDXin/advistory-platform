package top.atstudy.advistory.advistory.dao.dto;

import top.atstudy.component.base.BaseDTO;
import top.atstudy.component.enums.EnumAdvistoryCategory;
import top.atstudy.component.enums.EnumAdvistoryLevel;
import top.atstudy.component.enums.EnumAdvistoryType;
import top.atstudy.component.enums.EnumDeleted;
import java.io.Serializable;
import java.util.Date;

public class AdvistoryInfoDTO extends BaseDTO implements Serializable {
    private Long advistoryId;

    private EnumAdvistoryLevel advistoryLevel;

    private EnumAdvistoryCategory advistoryCategory;

    private EnumAdvistoryType advistoryType;

    private String title;

    private String digest;

    private Long favoriteNumber;

    private Long readNumber;

    private String coverImage;

    private Boolean recommendStatus;

    private Boolean stickStatus;

    private Boolean adStatus;

    private String link;

    private Long publishUserId;

    private String publishUserName;

    private Date publishOperationTime;

    private Date publishTime;

    private String author;

    private EnumDeleted deleted;

    private static final long serialVersionUID = 1L;

    public Long getAdvistoryId() {
        return advistoryId;
    }

    public void setAdvistoryId(Long advistoryId) {
        this.advistoryId = advistoryId;
    }

    public EnumAdvistoryLevel getAdvistoryLevel() {
        return advistoryLevel;
    }

    public void setAdvistoryLevel(EnumAdvistoryLevel advistoryLevel) {
        this.advistoryLevel = advistoryLevel;
    }

    public EnumAdvistoryCategory getAdvistoryCategory() {
        return advistoryCategory;
    }

    public void setAdvistoryCategory(EnumAdvistoryCategory advistoryCategory) {
        this.advistoryCategory = advistoryCategory;
    }

    public EnumAdvistoryType getAdvistoryType() {
        return advistoryType;
    }

    public void setAdvistoryType(EnumAdvistoryType advistoryType) {
        this.advistoryType = advistoryType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest == null ? null : digest.trim();
    }

    public Long getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(Long favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public Long getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(Long readNumber) {
        this.readNumber = readNumber;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
    }

    public Boolean getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(Boolean recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public Boolean getStickStatus() {
        return stickStatus;
    }

    public void setStickStatus(Boolean stickStatus) {
        this.stickStatus = stickStatus;
    }

    public Boolean getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(Boolean adStatus) {
        this.adStatus = adStatus;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public Long getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Long publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getPublishUserName() {
        return publishUserName;
    }

    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName == null ? null : publishUserName.trim();
    }

    public Date getPublishOperationTime() {
        return publishOperationTime;
    }

    public void setPublishOperationTime(Date publishOperationTime) {
        this.publishOperationTime = publishOperationTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public EnumDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(EnumDeleted deleted) {
        this.deleted = deleted;
    }
}