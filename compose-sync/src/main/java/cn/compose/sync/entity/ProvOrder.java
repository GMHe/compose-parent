package cn.compose.sync.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProvOrder implements Serializable {
    private Long id;

    private String orderNumber;

    private String platformOrderNo;

    private Long resourceId;

    private Long userId;

    private Integer status;

    private BigDecimal payAmount;

    private BigDecimal payAmountActual;

    private Integer validTime;

    private String payUrl;

    private Date payTime;

    private BigDecimal resorceAmount;

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getPlatformOrderNo() {
        return platformOrderNo;
    }

    public void setPlatformOrderNo(String platformOrderNo) {
        this.platformOrderNo = platformOrderNo == null ? null : platformOrderNo.trim();
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmountActual() {
        return payAmountActual;
    }

    public void setPayAmountActual(BigDecimal payAmountActual) {
        this.payAmountActual = payAmountActual;
    }

    public Integer getValidTime() {
        return validTime;
    }

    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl == null ? null : payUrl.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getResorceAmount() {
        return resorceAmount;
    }

    public void setResorceAmount(BigDecimal resorceAmount) {
        this.resorceAmount = resorceAmount;
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
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", platformOrderNo=").append(platformOrderNo);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", payAmountActual=").append(payAmountActual);
        sb.append(", validTime=").append(validTime);
        sb.append(", payUrl=").append(payUrl);
        sb.append(", payTime=").append(payTime);
        sb.append(", resorceAmount=").append(resorceAmount);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}