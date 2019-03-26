package com.booway.pojo;

import java.util.Date;

public class TVoteRecord {
    private Integer id;
    //投票人的openid
    private String voteOpenId;
    //投票人的UnionId
    private String voteUnionId;

    private Integer enterUserId;

    private String enterOpenId;

    private String enterUnionId;

    private Date voteTime;

    private Integer voteNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoteOpenId() {
        return voteOpenId;
    }

    public void setVoteOpenId(String voteOpenId) {
        this.voteOpenId = voteOpenId == null ? null : voteOpenId.trim();
    }

    public String getVoteUnionId() {
        return voteUnionId;
    }

    public void setVoteUnionId(String voteUnionId) {
        this.voteUnionId = voteUnionId == null ? null : voteUnionId.trim();
    }

    public Integer getEnterUserId() {
        return enterUserId;
    }

    public void setEnterUserId(Integer enterUserId) {
        this.enterUserId = enterUserId;
    }

    public String getEnterOpenId() {
        return enterOpenId;
    }

    public void setEnterOpenId(String enterOpenId) {
        this.enterOpenId = enterOpenId == null ? null : enterOpenId.trim();
    }

    public String getEnterUnionId() {
        return enterUnionId;
    }

    public void setEnterUnionId(String enterUnionId) {
        this.enterUnionId = enterUnionId == null ? null : enterUnionId.trim();
    }

    public Date getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(Date voteTime) {
        this.voteTime = voteTime;
    }

    public Integer getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(Integer voteNum) {
        this.voteNum = voteNum;
    }
}