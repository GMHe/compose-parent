package cn.compose.admin.entity;

import cn.compose.admin.config.JsonZoneTimeSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class UserToken implements Serializable {
    private Long id;

    private String uuid;

    private Long userId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using= JsonZoneTimeSerialize.class)
    private Date loginTime;

    private Integer state;

    private String userEmail;

    private String userName;

    private Integer status;

    private String createUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using= JsonZoneTimeSerialize.class)
    private Date createDate;

    private static final long serialVersionUID = 13445646465L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uuid=").append(uuid);
        sb.append(", userId=").append(userId);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", state=").append(state);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userName=").append(userName);
        sb.append(", status=").append(status);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}