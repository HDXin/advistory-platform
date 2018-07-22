package top.atstudy.component.feedback.dao.dto;

import top.atstudy.component.base.BaseDTO;
import top.atstudy.component.enums.EnumDeleted;
import java.io.Serializable;
import java.util.Date;

public class FeedbackDTO extends BaseDTO implements Serializable {
    private Long feedbackId;

    private String content;

    private Long applyUserId;

    private String applyUserPhoto;

    private String applyUserName;

    private Date applyTime;

    private String applyUserPhone;

    private EnumDeleted deleted;

    private static final long serialVersionUID = 1L;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserPhoto() {
        return applyUserPhoto;
    }

    public void setApplyUserPhoto(String applyUserPhoto) {
        this.applyUserPhoto = applyUserPhoto == null ? null : applyUserPhoto.trim();
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName == null ? null : applyUserName.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyUserPhone() {
        return applyUserPhone;
    }

    public void setApplyUserPhone(String applyUserPhone) {
        this.applyUserPhone = applyUserPhone == null ? null : applyUserPhone.trim();
    }

    public EnumDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(EnumDeleted deleted) {
        this.deleted = deleted;
    }
}