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
public class AdvistoryInfoReq implements Serializable {

    private Long advistoryId;

    private EnumAdvistoryLevel advistoryLevel;

    private EnumAdvistoryType advistoryType;

    private String title;

    private String digest;

    private Long favoriteNumber;

    private Long readNumber;

    private String coverImage;

    private Boolean recommendStatus;

    private Long publishUserId;

    private String publishUserName;

    private Date publishOperationTime;

    private Date publishTime;

    private String publishTimeStr;

    private String author;

    private EnumDeleted deleted;

    private List<AdvistoryDetailReq> details = new ArrayList<>();

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

    public String getPublishTimeStr() {
        return publishTimeStr;
    }

    public void setPublishTimeStr(String publishTimeStr) {
        this.publishTimeStr = publishTimeStr;
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

    public EnumAdvistoryLevel getAdvistoryLevel() {
        return advistoryLevel;
    }

    public void setAdvistoryLevel(EnumAdvistoryLevel advistoryLevel) {
        this.advistoryLevel = advistoryLevel;
    }

    public Boolean getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(Boolean recommendStatus) {
        this.recommendStatus = recommendStatus;
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
        this.publishUserName = publishUserName;
    }

    public Date getPublishOperationTime() {
        return publishOperationTime;
    }

    public void setPublishOperationTime(Date publishOperationTime) {
        this.publishOperationTime = publishOperationTime;
    }

    public EnumDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(EnumDeleted deleted) {
        this.deleted = deleted;
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
