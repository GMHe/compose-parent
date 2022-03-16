package cn.compose.open.entity;

import java.io.Serializable;
import java.util.Date;

public class ResourceBase implements Serializable {
    private Long id;

    private Long resourceId;

    private Long resourceTypeId;

    private String resourceName;

    private String resourceUrl;

    private String downloadPwd;

    private String resourcePassword;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getResourceTypeId() {
        return resourceTypeId;
    }

    public void setResourceTypeId(Long resourceTypeId) {
        this.resourceTypeId = resourceTypeId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public String getDownloadPwd() {
        return downloadPwd;
    }

    public void setDownloadPwd(String downloadPwd) {
        this.downloadPwd = downloadPwd == null ? null : downloadPwd.trim();
    }

    public String getResourcePassword() {
        return resourcePassword;
    }

    public void setResourcePassword(String resourcePassword) {
        this.resourcePassword = resourcePassword == null ? null : resourcePassword.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", resourceTypeId=").append(resourceTypeId);
        sb.append(", resourceName=").append(resourceName);
        sb.append(", resourceUrl=").append(resourceUrl);
        sb.append(", downloadPwd=").append(downloadPwd);
        sb.append(", resourcePassword=").append(resourcePassword);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}