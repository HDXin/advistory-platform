package top.atstudy.component.setting.dao.dto;

import top.atstudy.component.base.BaseDTO;
import top.atstudy.component.enums.EnumDeleted;
import java.io.Serializable;

public class SettingDTO extends BaseDTO implements Serializable {
    private Long settingId;

    private String configKey;

    private String configValue;

    private EnumDeleted deleted;

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

    public EnumDeleted getDeleted() {
        return deleted;
    }

    public void setDeleted(EnumDeleted deleted) {
        this.deleted = deleted;
    }
}