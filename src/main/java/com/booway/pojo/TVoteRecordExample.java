package com.booway.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TVoteRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TVoteRecordExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdIsNull() {
            addCriterion("VOTE_OPEN_ID is null");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdIsNotNull() {
            addCriterion("VOTE_OPEN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdEqualTo(String value) {
            addCriterion("VOTE_OPEN_ID =", value, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdNotEqualTo(String value) {
            addCriterion("VOTE_OPEN_ID <>", value, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdGreaterThan(String value) {
            addCriterion("VOTE_OPEN_ID >", value, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("VOTE_OPEN_ID >=", value, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdLessThan(String value) {
            addCriterion("VOTE_OPEN_ID <", value, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdLessThanOrEqualTo(String value) {
            addCriterion("VOTE_OPEN_ID <=", value, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdLike(String value) {
            addCriterion("VOTE_OPEN_ID like", value, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdNotLike(String value) {
            addCriterion("VOTE_OPEN_ID not like", value, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdIn(List<String> values) {
            addCriterion("VOTE_OPEN_ID in", values, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdNotIn(List<String> values) {
            addCriterion("VOTE_OPEN_ID not in", values, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdBetween(String value1, String value2) {
            addCriterion("VOTE_OPEN_ID between", value1, value2, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteOpenIdNotBetween(String value1, String value2) {
            addCriterion("VOTE_OPEN_ID not between", value1, value2, "voteOpenId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdIsNull() {
            addCriterion("VOTE_UNION_ID is null");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdIsNotNull() {
            addCriterion("VOTE_UNION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdEqualTo(String value) {
            addCriterion("VOTE_UNION_ID =", value, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdNotEqualTo(String value) {
            addCriterion("VOTE_UNION_ID <>", value, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdGreaterThan(String value) {
            addCriterion("VOTE_UNION_ID >", value, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdGreaterThanOrEqualTo(String value) {
            addCriterion("VOTE_UNION_ID >=", value, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdLessThan(String value) {
            addCriterion("VOTE_UNION_ID <", value, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdLessThanOrEqualTo(String value) {
            addCriterion("VOTE_UNION_ID <=", value, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdLike(String value) {
            addCriterion("VOTE_UNION_ID like", value, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdNotLike(String value) {
            addCriterion("VOTE_UNION_ID not like", value, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdIn(List<String> values) {
            addCriterion("VOTE_UNION_ID in", values, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdNotIn(List<String> values) {
            addCriterion("VOTE_UNION_ID not in", values, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdBetween(String value1, String value2) {
            addCriterion("VOTE_UNION_ID between", value1, value2, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteUnionIdNotBetween(String value1, String value2) {
            addCriterion("VOTE_UNION_ID not between", value1, value2, "voteUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdIsNull() {
            addCriterion("ENTER_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdIsNotNull() {
            addCriterion("ENTER_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdEqualTo(Integer value) {
            addCriterion("ENTER_USER_ID =", value, "enterUserId");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdNotEqualTo(Integer value) {
            addCriterion("ENTER_USER_ID <>", value, "enterUserId");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdGreaterThan(Integer value) {
            addCriterion("ENTER_USER_ID >", value, "enterUserId");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ENTER_USER_ID >=", value, "enterUserId");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdLessThan(Integer value) {
            addCriterion("ENTER_USER_ID <", value, "enterUserId");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("ENTER_USER_ID <=", value, "enterUserId");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdIn(List<Integer> values) {
            addCriterion("ENTER_USER_ID in", values, "enterUserId");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdNotIn(List<Integer> values) {
            addCriterion("ENTER_USER_ID not in", values, "enterUserId");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdBetween(Integer value1, Integer value2) {
            addCriterion("ENTER_USER_ID between", value1, value2, "enterUserId");
            return (Criteria) this;
        }

        public Criteria andEnterUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ENTER_USER_ID not between", value1, value2, "enterUserId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdIsNull() {
            addCriterion("ENTER_OPEN_ID is null");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdIsNotNull() {
            addCriterion("ENTER_OPEN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdEqualTo(String value) {
            addCriterion("ENTER_OPEN_ID =", value, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdNotEqualTo(String value) {
            addCriterion("ENTER_OPEN_ID <>", value, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdGreaterThan(String value) {
            addCriterion("ENTER_OPEN_ID >", value, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("ENTER_OPEN_ID >=", value, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdLessThan(String value) {
            addCriterion("ENTER_OPEN_ID <", value, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdLessThanOrEqualTo(String value) {
            addCriterion("ENTER_OPEN_ID <=", value, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdLike(String value) {
            addCriterion("ENTER_OPEN_ID like", value, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdNotLike(String value) {
            addCriterion("ENTER_OPEN_ID not like", value, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdIn(List<String> values) {
            addCriterion("ENTER_OPEN_ID in", values, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdNotIn(List<String> values) {
            addCriterion("ENTER_OPEN_ID not in", values, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdBetween(String value1, String value2) {
            addCriterion("ENTER_OPEN_ID between", value1, value2, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterOpenIdNotBetween(String value1, String value2) {
            addCriterion("ENTER_OPEN_ID not between", value1, value2, "enterOpenId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdIsNull() {
            addCriterion("ENTER_UNION_ID is null");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdIsNotNull() {
            addCriterion("ENTER_UNION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdEqualTo(String value) {
            addCriterion("ENTER_UNION_ID =", value, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdNotEqualTo(String value) {
            addCriterion("ENTER_UNION_ID <>", value, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdGreaterThan(String value) {
            addCriterion("ENTER_UNION_ID >", value, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdGreaterThanOrEqualTo(String value) {
            addCriterion("ENTER_UNION_ID >=", value, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdLessThan(String value) {
            addCriterion("ENTER_UNION_ID <", value, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdLessThanOrEqualTo(String value) {
            addCriterion("ENTER_UNION_ID <=", value, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdLike(String value) {
            addCriterion("ENTER_UNION_ID like", value, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdNotLike(String value) {
            addCriterion("ENTER_UNION_ID not like", value, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdIn(List<String> values) {
            addCriterion("ENTER_UNION_ID in", values, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdNotIn(List<String> values) {
            addCriterion("ENTER_UNION_ID not in", values, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdBetween(String value1, String value2) {
            addCriterion("ENTER_UNION_ID between", value1, value2, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andEnterUnionIdNotBetween(String value1, String value2) {
            addCriterion("ENTER_UNION_ID not between", value1, value2, "enterUnionId");
            return (Criteria) this;
        }

        public Criteria andVoteTimeIsNull() {
            addCriterion("VOTE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andVoteTimeIsNotNull() {
            addCriterion("VOTE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andVoteTimeEqualTo(Date value) {
            addCriterion("VOTE_TIME =", value, "voteTime");
            return (Criteria) this;
        }

        public Criteria andVoteTimeNotEqualTo(Date value) {
            addCriterion("VOTE_TIME <>", value, "voteTime");
            return (Criteria) this;
        }

        public Criteria andVoteTimeGreaterThan(Date value) {
            addCriterion("VOTE_TIME >", value, "voteTime");
            return (Criteria) this;
        }

        public Criteria andVoteTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("VOTE_TIME >=", value, "voteTime");
            return (Criteria) this;
        }

        public Criteria andVoteTimeLessThan(Date value) {
            addCriterion("VOTE_TIME <", value, "voteTime");
            return (Criteria) this;
        }

        public Criteria andVoteTimeLessThanOrEqualTo(Date value) {
            addCriterion("VOTE_TIME <=", value, "voteTime");
            return (Criteria) this;
        }

        public Criteria andVoteTimeIn(List<Date> values) {
            addCriterion("VOTE_TIME in", values, "voteTime");
            return (Criteria) this;
        }

        public Criteria andVoteTimeNotIn(List<Date> values) {
            addCriterion("VOTE_TIME not in", values, "voteTime");
            return (Criteria) this;
        }

        public Criteria andVoteTimeBetween(Date value1, Date value2) {
            addCriterion("VOTE_TIME between", value1, value2, "voteTime");
            return (Criteria) this;
        }

        public Criteria andVoteTimeNotBetween(Date value1, Date value2) {
            addCriterion("VOTE_TIME not between", value1, value2, "voteTime");
            return (Criteria) this;
        }

        public Criteria andVoteNumIsNull() {
            addCriterion("VOTE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andVoteNumIsNotNull() {
            addCriterion("VOTE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andVoteNumEqualTo(Integer value) {
            addCriterion("VOTE_NUM =", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumNotEqualTo(Integer value) {
            addCriterion("VOTE_NUM <>", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumGreaterThan(Integer value) {
            addCriterion("VOTE_NUM >", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOTE_NUM >=", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumLessThan(Integer value) {
            addCriterion("VOTE_NUM <", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumLessThanOrEqualTo(Integer value) {
            addCriterion("VOTE_NUM <=", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumIn(List<Integer> values) {
            addCriterion("VOTE_NUM in", values, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumNotIn(List<Integer> values) {
            addCriterion("VOTE_NUM not in", values, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_NUM between", value1, value2, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumNotBetween(Integer value1, Integer value2) {
            addCriterion("VOTE_NUM not between", value1, value2, "voteNum");
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