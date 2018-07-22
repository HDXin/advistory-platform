package top.atstudy.component.user.vo.resp;

import top.atstudy.component.base.BaseSpecFields;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumUserStatus;
import top.atstudy.component.enums.EnumUserType;
import top.atstudy.component.user.dao.dto.AppUserDTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AppUser 相应参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class AppUserResp extends BaseSpecFields implements Serializable {


    private Long userId;

    private String userName;

    private String openid;

    private String fullName;

    private String nickname;

    private String photo;

    private Boolean gender;

    private String mobile;

    private Boolean mobileValid;

    private String tel;

    private String email;

    private EnumUserStatus userStatus;

    private String idcardImageReverse;

    private String idcardImageFront;

    private String idcardName;

    private String idcardNo;

    private Date endTime;

    private Date beginTime;

    private EnumUserType userType;

    private Boolean idcardValid;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Boolean getMobileValid() {
        return mobileValid;
    }

    public void setMobileValid(Boolean mobileValid) {
        this.mobileValid = mobileValid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public EnumUserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(EnumUserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getIdcardImageReverse() {
        return idcardImageReverse;
    }

    public void setIdcardImageReverse(String idcardImageReverse) {
        this.idcardImageReverse = idcardImageReverse == null ? null : idcardImageReverse.trim();
    }

    public String getIdcardImageFront() {
        return idcardImageFront;
    }

    public void setIdcardImageFront(String idcardImageFront) {
        this.idcardImageFront = idcardImageFront == null ? null : idcardImageFront.trim();
    }

    public String getIdcardName() {
        return idcardName;
    }

    public void setIdcardName(String idcardName) {
        this.idcardName = idcardName == null ? null : idcardName.trim();
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo == null ? null : idcardNo.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public EnumUserType getUserType() {
        return userType;
    }

    public void setUserType(EnumUserType userType) {
        this.userType = userType;
    }

    public Boolean getIdcardValid() {
        return idcardValid;
    }

    public void setIdcardValid(Boolean idcardValid) {
        this.idcardValid = idcardValid;
    }


    public static AppUserResp parseSinglet(AppUserDTO target) {
        AppUserResp self = new AppUserResp();
        return self.parse(target);
    }

    public static List<AppUserResp> parseList(List<AppUserDTO> targets) {
        return targets.stream().map(AppUserResp::parseSinglet).collect(Collectors.toList());
    }

    public AppUserResp parse(AppUserDTO target) {
        if(target == null) {
            return null;
        }
        BeanUtils.copyProperties(target, this);
        return this;
    }

    public static AppUserDTO convertToDTO(AppUserResp resp) {
        return resp.convertToDTO();
    }

    public static List<AppUserDTO> convertToDTO(List<AppUserResp> resps) {
        return resps.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public AppUserDTO convertToDTO() {
        return BeanUtils.copyProperties(this, AppUserDTO.class);
    }
}
