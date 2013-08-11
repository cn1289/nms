<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath }</c:set>
    <form id="pagerForm" method="post" action="${contextPath }/macvendors">
        <input type="hidden" name="pageNumber" value="1" />
        <input type="hidden" name="pageSize" value="${pager.pageSize }" />
        <input type="hidden" name="orderBy" value="${pager.orderBy }" />
        <input type="hidden" name="orderType" value="${pager.orderType }" />
    </form>
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${contextPath }/macvendors" method="post">
			<div class="searchBar">
				<table class="searchContent" style="float:left">
					<tr>
						<td>
							关键字：<input type="text"   name="keywords"  value="${keywords}"/>
						</td>
						<td>
							<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
  	<div class="pageContent" >
		<div class="panelBar">
			<ul class="toolBar">
<%-- 				<li><a class="add" href="${contextPath }/SysParamManage/add.do" target="dialog" rel="addsysParamManage"  mask="true" height="300" width="500" title="添加系统参数"><span>添加</span></a></li> --%>
				<%-- <li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="names" href="${contextPath }/SysParamManage/delete.do" class="delete"><span>删除</span></a></li>	 --%>
<!-- 				<li class="line">line</li>	 -->
			</ul>
			<!-- 页面内导航2(放在.pageContent .panelBar 里) -->
			<div class="nav">
<!-- 				<span class="current">当前页面：</span> -->
<!-- 				<a href="#">系统功能</a> -->
<%-- 				<a href="${contextPath }/SysParamManage/list.do" target="navTab" rel="sysParamManage">系统参数管理</a> --%>
			</div>
			
		</div>
		<table class="list" width="100%" layoutH="93" asc="asc" desc="desc">
			<thead>
				<tr>
<!-- 					<th width="50px"><label><input type="checkbox" class="checkboxCtrl" group="names"/></label></th> -->
					<th >MAC Address</th>
					<th >Vendor name</th>
				</tr>
			</thead>
			<tbody>
 				<c:forEach items="${pager.list}" var="item">
				<tr>
<%-- 					<td><input type="checkbox" name="names" value="${item.name}"/></td> --%>
					<td>${item.macAddress}</td>
					<td>${item.vendor}</td>					
<!-- 					<td> -->
<%-- 					    <a title="编辑" target="dialog" href="${contextPath }/SysParamManage/edit.do?name=${item.name}" class="btnEdit" rel="editFrontServer"  mask="true" height="300" width="500">编辑</a><span class="separator" ></span> --%>
<%-- 						<a title="删除" target="ajaxTodo" href="${contextPath }/SysParamManage/delete.do?names=${item.name}" class="btnDel">删除</a>					 --%>
<!-- 					</td> -->
				</tr>				
				</c:forEach> 		
			</tbody>
		</table>
		<div class="panelBar" >
            <div class="pages">
                <span>显示</span>
                <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                    <option value="20" <c:if test="${pager.pageSize == 20}">selected</c:if> >20</option>
                    <option value="50" <c:if test="${pager.pageSize == 50}">selected</c:if> >50</option>
                    <option value="100" <c:if test="${pager.pageSize == 100}">selected</c:if> >100</option>
                    <option value="200" <c:if test="${pager.pageSize == 200}">selected</c:if> >200</option>
                    <option value="500" <c:if test="${pager.pageSize == 500}">selected</c:if> >500</option>
                </select>
                <span>条，共${pager.totalCount}条，共${pager.pageCount}页</span>
            </div>          
            <div class="pagination" targetType="navTab" totalCount="${pager.totalCount}" numPerPage="${pager.pageSize }" pageNumShown="10" currentPage="${pager.pageNumber }"></div>    
        </div>
	</div>
