
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />
这是编辑页面 this is edit page ${welcome_message}
<!-- 这里调回主页 -->
<portlet:renderURL var="home"/>   
<div>跳转到home页面 back to home <a href="${home}">home</a></div>