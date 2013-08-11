package com.acacia.nms.server.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类，含有部分对所有controller通用的工具方法.
 * 
 * @author zhonglizhi.
 */
public class Utils {

     private final static Logger log = LoggerFactory.getLogger(Utils.class);
    /**
     * Constructor.
     */
    private Utils() {
    }

    // AJAX输出，返回null
    public static String ajax(final HttpServletResponse response,
            final String content, final String type) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (final IOException e) {
            log.error("",e);
        }
        return null;
    }

    // AJAX输出文本，返回null
    public static String ajaxText(final HttpServletResponse response,
            final String text) {
        return ajax(response, text, "text/plain");
    }

    // AJAX输出HTML，返回null
    public static String ajaxHtml(final HttpServletResponse response,
            final String html) {
        return ajax(response, html, "text/html");
    }
    
    /**
     * AJAX输出alert提示信息，返回null
     * @param response
     * @param message   提示消息
     * @param isClose   是否关闭当前页面
     * @return
     */
    public static String ajaxAlert(final HttpServletResponse response,
            final String message,final boolean isClose) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script type=\"text/javascript\">");
        sb.append("     alert(\"" + message + "\");");
        if(isClose){
            sb.append("     window.close();");
        }
        sb.append("</script>");
        return ajax(response, sb.toString(), "text/html");
    }

    // AJAX输出XML，返回null
    public static String ajaxXml(final HttpServletResponse response,
            final String xml) {
        return ajax(response, xml, "text/xml");
    }

    // 根据字符串输出JSON，返回null
    public static String ajaxJson(final HttpServletResponse response,
            final String jsonString) {
        return ajax(response, jsonString, "text/html");
    }

    // 根据Map输出JSON，返回null
    public static String ajaxJson(final HttpServletResponse response,
            final Map<String, String> jsonMap) {
        final JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return ajax(response, jsonObject.toString(), "text/html");
    }

    /**
     * 根据DWZJsonBean输出JSON，返回null
     * 
     * @param response
     * @param dwzJsonBean
     * @return
     */
    public static String ajaxJson(final HttpServletResponse response,
            final DWZJsonBean dwzJsonBean) {
        final JSONObject jsonObject = JSONObject.fromObject(dwzJsonBean);
        return ajax(response, jsonObject.toString(), "text/html");
    }

    /**
     * 通过navTab提交的消息<br>
     * 输出JSON成功消息，返回null
     * 
     * @param response
     * @param message
     *            消息
     * @param navTabId
     *            需刷新的navTabId
     * @param forwardUrl
     *            访问地址
     * @return
     */
    public static String ajaxJsonNavTabSuccessMessage(
            final HttpServletResponse response, final String message,
            final String navTabId, final String forwardUrl) {
        final DWZJsonBean dwzJsonBean = new DWZJsonBean();
        dwzJsonBean.setStatusCode("200");
        dwzJsonBean.setCallbackType("forward");
        dwzJsonBean.setNavTabId(navTabId);
        dwzJsonBean.setMessage(message);
        dwzJsonBean.setForwardUrl(forwardUrl);
        final JSONObject jsonObject = JSONObject.fromObject(dwzJsonBean);
        return ajax(response, jsonObject.toString(), "text/html");
    }

    /**
     * 通过Dialog提交的消息<br>
     * 输出JSON成功消息，返回null
     * 
     * @param response
     * @param message
     *            消息
     * @param navTabId
     *            需刷新的navTabId
     * @param forwardUrl
     *            访问地址
     * @return
     */
    public static String ajaxJsonDialogSuccessMessage(
            final HttpServletResponse response, final String message,
            final String navTabId, final String forwardUrl) {
        return ajaxJsonDialogSuccessMessage(response,message,navTabId,forwardUrl,true,"navTab");
    }
    /**
     * 通过Dialog提交的消息<br>
     * 输出JSON成功消息，返回null
     * 
     * @param response
     * @param message
     *            消息
     * @param navTabId
     *            需刷新的navTabId
     * @param forwardUrl
     *            访问地址
     * @param closeCurrent
     *            是否关闭本对话框
     * @param refreshTargetType
     *            刷新对象类型：dialog或navTab
     * @return
     */
    public static String ajaxJsonDialogSuccessMessage(
            final HttpServletResponse response, final String message,
            final String navTabId, final String forwardUrl,final Boolean closeCurrent,final String refreshTargetType) {
        final DWZJsonBean dwzJsonBean = new DWZJsonBean();
        dwzJsonBean.setStatusCode("200");
        if(closeCurrent!=null && closeCurrent){
            dwzJsonBean.setCallbackType("closeCurrent");
        }
        dwzJsonBean.setNavTabId(navTabId);
        dwzJsonBean.setMessage(message);
        dwzJsonBean.setForwardUrl(forwardUrl);
        dwzJsonBean.setRefreshTargetType(refreshTargetType);
        final JSONObject jsonObject = JSONObject.fromObject(dwzJsonBean);
        return ajax(response, jsonObject.toString(), "text/html");
    }

    public static String ajaxJsonDialogErrorMessage(
            final HttpServletResponse response, final String message,
            final String navTabId, final String forwardUrl) {
        final DWZJsonBean dwzJsonBean = new DWZJsonBean();
        dwzJsonBean.setStatusCode("300");
        dwzJsonBean.setCallbackType("closeCurrent");
        dwzJsonBean.setNavTabId(navTabId);
        dwzJsonBean.setMessage(message);
        dwzJsonBean.setForwardUrl(forwardUrl);
        final JSONObject jsonObject = JSONObject.fromObject(dwzJsonBean);
        return ajax(response, jsonObject.toString(), "text/html");
    }

}
