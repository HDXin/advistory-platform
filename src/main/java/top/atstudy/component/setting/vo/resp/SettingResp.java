package top.atstudy.component.setting.vo.resp;

import top.atstudy.component.base.BaseSpecFields;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.setting.dao.dto.SettingDTO;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Setting 相应参数模板
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public class SettingResp extends BaseSpecFields implements Serializable {


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


    public static SettingResp parseSinglet(SettingDTO target) {
        SettingResp self = new SettingResp();
        return self.parse(target);
    }

    public static List<SettingResp> parseList(List<SettingDTO> targets) {
        return targets.stream().map(SettingResp::parseSinglet).collect(Collectors.toList());
    }

    public SettingResp parse(SettingDTO target) {
        if(target == null) {
            return null;
        }
        BeanUtils.copyProperties(target, this);
        return this;
    }

    public static SettingDTO convertToDTO(SettingResp resp) {
        return resp.convertToDTO();
    }

    public static List<SettingDTO> convertToDTO(List<SettingResp> resps) {
        return resps.stream().map(v -> v.convertToDTO()).collect(Collectors.toList());
    }

    public SettingDTO convertToDTO() {
        return BeanUtils.copyProperties(this, SettingDTO.class);
    }
}
