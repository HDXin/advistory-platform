package top.atstudy.component.feedback.vo.req;

import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.feedback.dao.dto.FeedbackDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Feedback 请求参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class FeedbackReq implements Serializable {


    private Long feedbackId;

    private String content;

    private Long applyUserId;

    private String applyUserPhoto;

    private String applyUserName;

    private Date applyTime;

    private String applyUserPhone;

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


    public static FeedbackDTO convertToDTO(FeedbackReq req) {
        return req.convertToDTO();
    }

    public static List<FeedbackDTO> convertToDTO(List<FeedbackReq> reqs) {
        return reqs.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public FeedbackDTO convertToDTO() {
        return BeanUtils.copyProperties(this, FeedbackDTO.class);
    }
}
