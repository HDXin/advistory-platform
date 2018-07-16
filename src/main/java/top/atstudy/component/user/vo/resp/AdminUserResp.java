package top.atstudy.component.user.vo.resp;

import top.atstudy.component.base.BaseSpecFields;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.enums.EnumUserStatus;
import top.atstudy.component.user.dao.dto.AdminUserDTO;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AdminUser 相应参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class AdminUserResp extends BaseSpecFields implements Serializable {


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


    public static AdminUserResp parseSinglet(AdminUserDTO target) {
        AdminUserResp self = new AdminUserResp();
        return self.parse(target);
    }

    public static List<AdminUserResp> parseList(List<AdminUserDTO> targets) {
        return targets.stream().map(AdminUserResp::parseSinglet).collect(Collectors.toList());
    }

    public AdminUserResp parse(AdminUserDTO target) {
        if(target == null) {
            return null;
        }
        BeanUtils.copyProperties(target, this);
        return this;
    }

    public static AdminUserDTO convertToDTO(AdminUserResp resp) {
        return resp.convertToDTO();
    }

    public static List<AdminUserDTO> convertToDTO(List<AdminUserResp> resps) {
        return resps.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public AdminUserDTO convertToDTO() {
        return BeanUtils.copyProperties(this, AdminUserDTO.class);
    }
}
