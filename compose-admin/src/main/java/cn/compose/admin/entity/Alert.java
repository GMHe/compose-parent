package cn.compose.admin.entity;

import java.io.Serializable;
import java.util.Date;

public class Alert implements Serializable {
    private Long id;

    private Long categoryItemId;

    private String name;

    private Long categoryEnvId;

    private Integer status;

    private String filter;

    private String threshold;

    private String thresholdDescription;

    private Integer configSyncStatus;

    private Date configSyncTime;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private String filterDescription;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryItemId() {
        return categoryItemId;
    }

    public void setCategoryItemId(Long categoryItemId) {
        this.categoryItemId = categoryItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCategoryEnvId() {
        return categoryEnvId;
    }

    public void setCategoryEnvId(Long categoryEnvId) {
        this.categoryEnvId = categoryEnvId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter == null ? null : filter.trim();
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold == null ? null : threshold.trim();
    }

    public String getThresholdDescription() {
        return thresholdDescription;
    }

    public void setThresholdDescription(String thresholdDescription) {
        this.thresholdDescription = thresholdDescription == null ? null : thresholdDescription.trim();
    }

    public Integer getConfigSyncStatus() {
        return configSyncStatus;
    }

    public void setConfigSyncStatus(Integer configSyncStatus) {
        this.configSyncStatus = configSyncStatus;
    }

    public Date getConfigSyncTime() {
        return configSyncTime;
    }

    public void setConfigSyncTime(Date configSyncTime) {
        this.configSyncTime = configSyncTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getFilterDescription() {
        return filterDescription;
    }

    public void setFilterDescription(String filterDescription) {
        this.filterDescription = filterDescription == null ? null : filterDescription.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", categoryItemId=").append(categoryItemId);
        sb.append(", name=").append(name);
        sb.append(", categoryEnvId=").append(categoryEnvId);
        sb.append(", status=").append(status);
        sb.append(", filter=").append(filter);
        sb.append(", threshold=").append(threshold);
        sb.append(", thresholdDescription=").append(thresholdDescription);
        sb.append(", configSyncStatus=").append(configSyncStatus);
        sb.append(", configSyncTime=").append(configSyncTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", filterDescription=").append(filterDescription);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}