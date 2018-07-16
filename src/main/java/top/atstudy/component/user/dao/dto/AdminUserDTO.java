package top.atstudy.component.user.dao.dto;

import top.atstudy.component.base.BaseDTO;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumUserStatus;

import java.io.Serializable;

public class AdminUserDTO extends BaseDTO implements Serializable {
    private Long userId;

    private String userName;

    private String password;

    private String passwordSalt;

    private String photo;

    private Boolean gender;

    private String mobile;

    private String tel;

    private String email;

    private EnumUserStatus userStatus;

    private EnumDeleted deleted;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt == null ? null : passwordSalt.trim();
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

    public EnumDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(EnumDeleted deleted) {
        this.deleted = deleted;
    }
}