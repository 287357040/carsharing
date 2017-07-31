package com.carsharing.server.entity;

import java.io.Serializable;

/**
 * Created by hucl on 2017/7/14.
 * 系统推送给用户的消息实体
 */
@SuppressWarnings("serial")
public class UserMessage implements Serializable {
    private Integer msgId;

    private String msgTitle;

    private String msgContent;

    private String userNo;

    private Integer msgType;

    private Integer state;

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
