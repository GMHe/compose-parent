package cn.compose.open.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlertExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public AlertExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdIsNull() {
            addCriterion("category_item_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdIsNotNull() {
            addCriterion("category_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdEqualTo(Long value) {
            addCriterion("category_item_id =", value, "categoryItemId");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdNotEqualTo(Long value) {
            addCriterion("category_item_id <>", value, "categoryItemId");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdGreaterThan(Long value) {
            addCriterion("category_item_id >", value, "categoryItemId");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("category_item_id >=", value, "categoryItemId");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdLessThan(Long value) {
            addCriterion("category_item_id <", value, "categoryItemId");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdLessThanOrEqualTo(Long value) {
            addCriterion("category_item_id <=", value, "categoryItemId");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdIn(List<Long> values) {
            addCriterion("category_item_id in", values, "categoryItemId");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdNotIn(List<Long> values) {
            addCriterion("category_item_id not in", values, "categoryItemId");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdBetween(Long value1, Long value2) {
            addCriterion("category_item_id between", value1, value2, "categoryItemId");
            return (Criteria) this;
        }

        public Criteria andCategoryItemIdNotBetween(Long value1, Long value2) {
            addCriterion("category_item_id not between", value1, value2, "categoryItemId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdIsNull() {
            addCriterion("category_env_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdIsNotNull() {
            addCriterion("category_env_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdEqualTo(Long value) {
            addCriterion("category_env_id =", value, "categoryEnvId");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdNotEqualTo(Long value) {
            addCriterion("category_env_id <>", value, "categoryEnvId");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdGreaterThan(Long value) {
            addCriterion("category_env_id >", value, "categoryEnvId");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdGreaterThanOrEqualTo(Long value) {
            addCriterion("category_env_id >=", value, "categoryEnvId");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdLessThan(Long value) {
            addCriterion("category_env_id <", value, "categoryEnvId");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdLessThanOrEqualTo(Long value) {
            addCriterion("category_env_id <=", value, "categoryEnvId");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdIn(List<Long> values) {
            addCriterion("category_env_id in", values, "categoryEnvId");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdNotIn(List<Long> values) {
            addCriterion("category_env_id not in", values, "categoryEnvId");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdBetween(Long value1, Long value2) {
            addCriterion("category_env_id between", value1, value2, "categoryEnvId");
            return (Criteria) this;
        }

        public Criteria andCategoryEnvIdNotBetween(Long value1, Long value2) {
            addCriterion("category_env_id not between", value1, value2, "categoryEnvId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andFilterIsNull() {
            addCriterion("`filter` is null");
            return (Criteria) this;
        }

        public Criteria andFilterIsNotNull() {
            addCriterion("`filter` is not null");
            return (Criteria) this;
        }

        public Criteria andFilterEqualTo(String value) {
            addCriterion("`filter` =", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotEqualTo(String value) {
            addCriterion("`filter` <>", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterGreaterThan(String value) {
            addCriterion("`filter` >", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterGreaterThanOrEqualTo(String value) {
            addCriterion("`filter` >=", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterLessThan(String value) {
            addCriterion("`filter` <", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterLessThanOrEqualTo(String value) {
            addCriterion("`filter` <=", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterLike(String value) {
            addCriterion("`filter` like", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotLike(String value) {
            addCriterion("`filter` not like", value, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterIn(List<String> values) {
            addCriterion("`filter` in", values, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotIn(List<String> values) {
            addCriterion("`filter` not in", values, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterBetween(String value1, String value2) {
            addCriterion("`filter` between", value1, value2, "filter");
            return (Criteria) this;
        }

        public Criteria andFilterNotBetween(String value1, String value2) {
            addCriterion("`filter` not between", value1, value2, "filter");
            return (Criteria) this;
        }

        public Criteria andThresholdIsNull() {
            addCriterion("threshold is null");
            return (Criteria) this;
        }

        public Criteria andThresholdIsNotNull() {
            addCriterion("threshold is not null");
            return (Criteria) this;
        }

        public Criteria andThresholdEqualTo(String value) {
            addCriterion("threshold =", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdNotEqualTo(String value) {
            addCriterion("threshold <>", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdGreaterThan(String value) {
            addCriterion("threshold >", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdGreaterThanOrEqualTo(String value) {
            addCriterion("threshold >=", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdLessThan(String value) {
            addCriterion("threshold <", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdLessThanOrEqualTo(String value) {
            addCriterion("threshold <=", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdLike(String value) {
            addCriterion("threshold like", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdNotLike(String value) {
            addCriterion("threshold not like", value, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdIn(List<String> values) {
            addCriterion("threshold in", values, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdNotIn(List<String> values) {
            addCriterion("threshold not in", values, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdBetween(String value1, String value2) {
            addCriterion("threshold between", value1, value2, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdNotBetween(String value1, String value2) {
            addCriterion("threshold not between", value1, value2, "threshold");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionIsNull() {
            addCriterion("threshold_description is null");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionIsNotNull() {
            addCriterion("threshold_description is not null");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionEqualTo(String value) {
            addCriterion("threshold_description =", value, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionNotEqualTo(String value) {
            addCriterion("threshold_description <>", value, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionGreaterThan(String value) {
            addCriterion("threshold_description >", value, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("threshold_description >=", value, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionLessThan(String value) {
            addCriterion("threshold_description <", value, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionLessThanOrEqualTo(String value) {
            addCriterion("threshold_description <=", value, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionLike(String value) {
            addCriterion("threshold_description like", value, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionNotLike(String value) {
            addCriterion("threshold_description not like", value, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionIn(List<String> values) {
            addCriterion("threshold_description in", values, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionNotIn(List<String> values) {
            addCriterion("threshold_description not in", values, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionBetween(String value1, String value2) {
            addCriterion("threshold_description between", value1, value2, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andThresholdDescriptionNotBetween(String value1, String value2) {
            addCriterion("threshold_description not between", value1, value2, "thresholdDescription");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusIsNull() {
            addCriterion("config_sync_status is null");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusIsNotNull() {
            addCriterion("config_sync_status is not null");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusEqualTo(Integer value) {
            addCriterion("config_sync_status =", value, "configSyncStatus");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusNotEqualTo(Integer value) {
            addCriterion("config_sync_status <>", value, "configSyncStatus");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusGreaterThan(Integer value) {
            addCriterion("config_sync_status >", value, "configSyncStatus");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("config_sync_status >=", value, "configSyncStatus");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusLessThan(Integer value) {
            addCriterion("config_sync_status <", value, "configSyncStatus");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusLessThanOrEqualTo(Integer value) {
            addCriterion("config_sync_status <=", value, "configSyncStatus");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusIn(List<Integer> values) {
            addCriterion("config_sync_status in", values, "configSyncStatus");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusNotIn(List<Integer> values) {
            addCriterion("config_sync_status not in", values, "configSyncStatus");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusBetween(Integer value1, Integer value2) {
            addCriterion("config_sync_status between", value1, value2, "configSyncStatus");
            return (Criteria) this;
        }

        public Criteria andConfigSyncStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("config_sync_status not between", value1, value2, "configSyncStatus");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeIsNull() {
            addCriterion("config_sync_time is null");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeIsNotNull() {
            addCriterion("config_sync_time is not null");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeEqualTo(Date value) {
            addCriterion("config_sync_time =", value, "configSyncTime");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeNotEqualTo(Date value) {
            addCriterion("config_sync_time <>", value, "configSyncTime");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeGreaterThan(Date value) {
            addCriterion("config_sync_time >", value, "configSyncTime");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("config_sync_time >=", value, "configSyncTime");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeLessThan(Date value) {
            addCriterion("config_sync_time <", value, "configSyncTime");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeLessThanOrEqualTo(Date value) {
            addCriterion("config_sync_time <=", value, "configSyncTime");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeIn(List<Date> values) {
            addCriterion("config_sync_time in", values, "configSyncTime");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeNotIn(List<Date> values) {
            addCriterion("config_sync_time not in", values, "configSyncTime");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeBetween(Date value1, Date value2) {
            addCriterion("config_sync_time between", value1, value2, "configSyncTime");
            return (Criteria) this;
        }

        public Criteria andConfigSyncTimeNotBetween(Date value1, Date value2) {
            addCriterion("config_sync_time not between", value1, value2, "configSyncTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}