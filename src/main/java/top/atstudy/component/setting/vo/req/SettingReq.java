package top.atstudy.component.setting.vo.req;

import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.setting.dao.dto.SettingDTO;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Setting 请求参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class SettingReq implements Serializable {


    private Long settingId;

    private String configKey;

    private String configValue;

    private static final long serialVersionUID = 1L;

    public Long getSettingId() {
        return settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }


    public static SettingDTO convertToDTO(SettingReq req) {
        return req.convertToDTO();
    }

    public static List<SettingDTO> convertToDTO(List<SettingReq> reqs) {
        return reqs.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public SettingDTO convertToDTO() {
        return BeanUtils.copyProperties(this, SettingDTO.class);
    }
}
