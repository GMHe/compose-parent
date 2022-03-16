package cn.compose.admin.entity;

import java.io.Serializable;
import java.util.Date;

public class Source implements Serializable {
    private Long id;

    private Long sourceGroupId;

    private String sign;

    private String name;

    private Integer partitionNumber;

    private Integer replicationFactor;

    private String hashKey;

    private String keyTypeMapping;

    private Date lastInputTime;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceGroupId() {
        return sourceGroupId;
    }

    public void setSourceGroupId(Long sourceGroupId) {
        this.sourceGroupId = sourceGroupId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPartitionNumber() {
        return partitionNumber;
    }

    public void setPartitionNumber(Integer partitionNumber) {
        this.partitionNumber = partitionNumber;
    }

    public Integer getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(Integer replicationFactor) {
        this.replicationFactor = replicationFactor;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey == null ? null : hashKey.trim();
    }

    public String getKeyTypeMapping() {
        return keyTypeMapping;
    }

    public void setKeyTypeMapping(String keyTypeMapping) {
        this.keyTypeMapping = keyTypeMapping == null ? null : keyTypeMapping.trim();
    }

    public Date getLastInputTime() {
        return lastInputTime;
    }

    public void setLastInputTime(Date lastInputTime) {
        this.lastInputTime = lastInputTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        sb.append(", sourceGroupId=").append(sourceGroupId);
        sb.append(", sign=").append(sign);
        sb.append(", name=").append(name);
        sb.append(", partitionNumber=").append(partitionNumber);
        sb.append(", replicationFactor=").append(replicationFactor);
        sb.append(", hashKey=").append(hashKey);
        sb.append(", keyTypeMapping=").append(keyTypeMapping);
        sb.append(", lastInputTime=").append(lastInputTime);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}