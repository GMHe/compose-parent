package cn.compose.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public SourceExample() {
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

        public Criteria andSourceGroupIdIsNull() {
            addCriterion("source_group_id is null");
            return (Criteria) this;
        }

        public Criteria andSourceGroupIdIsNotNull() {
            addCriterion("source_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andSourceGroupIdEqualTo(Long value) {
            addCriterion("source_group_id =", value, "sourceGroupId");
            return (Criteria) this;
        }

        public Criteria andSourceGroupIdNotEqualTo(Long value) {
            addCriterion("source_group_id <>", value, "sourceGroupId");
            return (Criteria) this;
        }

        public Criteria andSourceGroupIdGreaterThan(Long value) {
            addCriterion("source_group_id >", value, "sourceGroupId");
            return (Criteria) this;
        }

        public Criteria andSourceGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("source_group_id >=", value, "sourceGroupId");
            return (Criteria) this;
        }

        public Criteria andSourceGroupIdLessThan(Long value) {
            addCriterion("source_group_id <", value, "sourceGroupId");
            return (Criteria) this;
        }

        public Criteria andSourceGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("source_group_id <=", value, "sourceGroupId");
            return (Criteria) this;
        }

        public Criteria andSourceGroupIdIn(List<Long> values) {
            addCriterion("source_group_id in", values, "sourceGroupId");
            return (Criteria) this;
        }

        public Criteria andSourceGroupIdNotIn(List<Long> values) {
            addCriterion("source_group_id not in", values, "sourceGroupId");
            return (Criteria) this;
        }

        public Criteria andSourceGroupIdBetween(Long value1, Long value2) {
            addCriterion("source_group_id between", value1, value2, "sourceGroupId");
            return (Criteria) this;
        }

        public Criteria andSourceGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("source_group_id not between", value1, value2, "sourceGroupId");
            return (Criteria) this;
        }

        public Criteria andSignIsNull() {
            addCriterion("sign is null");
            return (Criteria) this;
        }

        public Criteria andSignIsNotNull() {
            addCriterion("sign is not null");
            return (Criteria) this;
        }

        public Criteria andSignEqualTo(String value) {
            addCriterion("sign =", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotEqualTo(String value) {
            addCriterion("sign <>", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThan(String value) {
            addCriterion("sign >", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignGreaterThanOrEqualTo(String value) {
            addCriterion("sign >=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThan(String value) {
            addCriterion("sign <", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLessThanOrEqualTo(String value) {
            addCriterion("sign <=", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignLike(String value) {
            addCriterion("sign like", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotLike(String value) {
            addCriterion("sign not like", value, "sign");
            return (Criteria) this;
        }

        public Criteria andSignIn(List<String> values) {
            addCriterion("sign in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotIn(List<String> values) {
            addCriterion("sign not in", values, "sign");
            return (Criteria) this;
        }

        public Criteria andSignBetween(String value1, String value2) {
            addCriterion("sign between", value1, value2, "sign");
            return (Criteria) this;
        }

        public Criteria andSignNotBetween(String value1, String value2) {
            addCriterion("sign not between", value1, value2, "sign");
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

        public Criteria andPartitionNumberIsNull() {
            addCriterion("partition_number is null");
            return (Criteria) this;
        }

        public Criteria andPartitionNumberIsNotNull() {
            addCriterion("partition_number is not null");
            return (Criteria) this;
        }

        public Criteria andPartitionNumberEqualTo(Integer value) {
            addCriterion("partition_number =", value, "partitionNumber");
            return (Criteria) this;
        }

        public Criteria andPartitionNumberNotEqualTo(Integer value) {
            addCriterion("partition_number <>", value, "partitionNumber");
            return (Criteria) this;
        }

        public Criteria andPartitionNumberGreaterThan(Integer value) {
            addCriterion("partition_number >", value, "partitionNumber");
            return (Criteria) this;
        }

        public Criteria andPartitionNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("partition_number >=", value, "partitionNumber");
            return (Criteria) this;
        }

        public Criteria andPartitionNumberLessThan(Integer value) {
            addCriterion("partition_number <", value, "partitionNumber");
            return (Criteria) this;
        }

        public Criteria andPartitionNumberLessThanOrEqualTo(Integer value) {
            addCriterion("partition_number <=", value, "partitionNumber");
            return (Criteria) this;
        }

        public Criteria andPartitionNumberIn(List<Integer> values) {
            addCriterion("partition_number in", values, "partitionNumber");
            return (Criteria) this;
        }

        public Criteria andPartitionNumberNotIn(List<Integer> values) {
            addCriterion("partition_number not in", values, "partitionNumber");
            return (Criteria) this;
        }

        public Criteria andPartitionNumberBetween(Integer value1, Integer value2) {
            addCriterion("partition_number between", value1, value2, "partitionNumber");
            return (Criteria) this;
        }

        public Criteria andPartitionNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("partition_number not between", value1, value2, "partitionNumber");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorIsNull() {
            addCriterion("replication_factor is null");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorIsNotNull() {
            addCriterion("replication_factor is not null");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorEqualTo(Integer value) {
            addCriterion("replication_factor =", value, "replicationFactor");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorNotEqualTo(Integer value) {
            addCriterion("replication_factor <>", value, "replicationFactor");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorGreaterThan(Integer value) {
            addCriterion("replication_factor >", value, "replicationFactor");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorGreaterThanOrEqualTo(Integer value) {
            addCriterion("replication_factor >=", value, "replicationFactor");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorLessThan(Integer value) {
            addCriterion("replication_factor <", value, "replicationFactor");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorLessThanOrEqualTo(Integer value) {
            addCriterion("replication_factor <=", value, "replicationFactor");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorIn(List<Integer> values) {
            addCriterion("replication_factor in", values, "replicationFactor");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorNotIn(List<Integer> values) {
            addCriterion("replication_factor not in", values, "replicationFactor");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorBetween(Integer value1, Integer value2) {
            addCriterion("replication_factor between", value1, value2, "replicationFactor");
            return (Criteria) this;
        }

        public Criteria andReplicationFactorNotBetween(Integer value1, Integer value2) {
            addCriterion("replication_factor not between", value1, value2, "replicationFactor");
            return (Criteria) this;
        }

        public Criteria andHashKeyIsNull() {
            addCriterion("hash_key is null");
            return (Criteria) this;
        }

        public Criteria andHashKeyIsNotNull() {
            addCriterion("hash_key is not null");
            return (Criteria) this;
        }

        public Criteria andHashKeyEqualTo(String value) {
            addCriterion("hash_key =", value, "hashKey");
            return (Criteria) this;
        }

        public Criteria andHashKeyNotEqualTo(String value) {
            addCriterion("hash_key <>", value, "hashKey");
            return (Criteria) this;
        }

        public Criteria andHashKeyGreaterThan(String value) {
            addCriterion("hash_key >", value, "hashKey");
            return (Criteria) this;
        }

        public Criteria andHashKeyGreaterThanOrEqualTo(String value) {
            addCriterion("hash_key >=", value, "hashKey");
            return (Criteria) this;
        }

        public Criteria andHashKeyLessThan(String value) {
            addCriterion("hash_key <", value, "hashKey");
            return (Criteria) this;
        }

        public Criteria andHashKeyLessThanOrEqualTo(String value) {
            addCriterion("hash_key <=", value, "hashKey");
            return (Criteria) this;
        }

        public Criteria andHashKeyLike(String value) {
            addCriterion("hash_key like", value, "hashKey");
            return (Criteria) this;
        }

        public Criteria andHashKeyNotLike(String value) {
            addCriterion("hash_key not like", value, "hashKey");
            return (Criteria) this;
        }

        public Criteria andHashKeyIn(List<String> values) {
            addCriterion("hash_key in", values, "hashKey");
            return (Criteria) this;
        }

        public Criteria andHashKeyNotIn(List<String> values) {
            addCriterion("hash_key not in", values, "hashKey");
            return (Criteria) this;
        }

        public Criteria andHashKeyBetween(String value1, String value2) {
            addCriterion("hash_key between", value1, value2, "hashKey");
            return (Criteria) this;
        }

        public Criteria andHashKeyNotBetween(String value1, String value2) {
            addCriterion("hash_key not between", value1, value2, "hashKey");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingIsNull() {
            addCriterion("key_type_mapping is null");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingIsNotNull() {
            addCriterion("key_type_mapping is not null");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingEqualTo(String value) {
            addCriterion("key_type_mapping =", value, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingNotEqualTo(String value) {
            addCriterion("key_type_mapping <>", value, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingGreaterThan(String value) {
            addCriterion("key_type_mapping >", value, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingGreaterThanOrEqualTo(String value) {
            addCriterion("key_type_mapping >=", value, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingLessThan(String value) {
            addCriterion("key_type_mapping <", value, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingLessThanOrEqualTo(String value) {
            addCriterion("key_type_mapping <=", value, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingLike(String value) {
            addCriterion("key_type_mapping like", value, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingNotLike(String value) {
            addCriterion("key_type_mapping not like", value, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingIn(List<String> values) {
            addCriterion("key_type_mapping in", values, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingNotIn(List<String> values) {
            addCriterion("key_type_mapping not in", values, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingBetween(String value1, String value2) {
            addCriterion("key_type_mapping between", value1, value2, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andKeyTypeMappingNotBetween(String value1, String value2) {
            addCriterion("key_type_mapping not between", value1, value2, "keyTypeMapping");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeIsNull() {
            addCriterion("last_input_time is null");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeIsNotNull() {
            addCriterion("last_input_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeEqualTo(Date value) {
            addCriterion("last_input_time =", value, "lastInputTime");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeNotEqualTo(Date value) {
            addCriterion("last_input_time <>", value, "lastInputTime");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeGreaterThan(Date value) {
            addCriterion("last_input_time >", value, "lastInputTime");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_input_time >=", value, "lastInputTime");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeLessThan(Date value) {
            addCriterion("last_input_time <", value, "lastInputTime");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_input_time <=", value, "lastInputTime");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeIn(List<Date> values) {
            addCriterion("last_input_time in", values, "lastInputTime");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeNotIn(List<Date> values) {
            addCriterion("last_input_time not in", values, "lastInputTime");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeBetween(Date value1, Date value2) {
            addCriterion("last_input_time between", value1, value2, "lastInputTime");
            return (Criteria) this;
        }

        public Criteria andLastInputTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_input_time not between", value1, value2, "lastInputTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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