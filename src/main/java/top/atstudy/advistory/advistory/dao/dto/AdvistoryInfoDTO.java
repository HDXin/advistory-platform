package top.atstudy.advistory.advistory.dao.dto;

import top.atstudy.component.base.BaseDTO;
import top.atstudy.component.enums.EnumAdvistoryType;
import top.atstudy.component.enums.EnumDeleted;
import java.io.Serializable;
import java.util.Date;

public class AdvistoryInfoDTO extends BaseDTO implements Serializable {
    private Long advistoryId;

    private EnumAdvistoryType advistoryType;

    private String title;

    private String digest;

    private Long favoriteNumber;

    private Long readNumber;

    private Date publishTime;

    private String coverImage;

    private String author;

    private EnumDeleted deleted;

    private static final long serialVersionUID = 1L;

    public Long getAdvistoryId() {
        return advistoryId;
    }

    public void setAdvistoryId(Long advistoryId) {
        this.advistoryId = advistoryId;
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
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