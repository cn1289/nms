package com.acacia.nms.server.view;

/**
 * dwz需要返回的json数据格式
 * 
 * @author zhonglizhi
 * 
 */
public class DWZJsonBean {

    private String statusCode; // 状态码 200 成功、300 失败、301 超时
    private String message; // 提示信息
    private String navTabId; // 需要刷新的navTabId
    private String rel;
    private String callbackType;// 回调类型：closeCurrent：关闭navTabId指定的navTab，forward：刷新navTabId指定的navTab，forwardConfirm：确认刷新
    private String forwardUrl; // 转发的地址
    private String refreshTargetType; //刷新对象类型：dialog或navTab

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(final String navTabId) {
        this.navTabId = navTabId;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(final String rel) {
        this.rel = rel;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(final String callbackType) {
        this.callbackType = callbackType;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(final String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public String getRefreshTargetType() {
        return refreshTargetType;
    }

    public void setRefreshTargetType(String refreshTargetType) {
        this.refreshTargetType = refreshTargetType;
    }
    
}
