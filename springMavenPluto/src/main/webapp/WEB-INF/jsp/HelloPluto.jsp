<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<portlet:actionURL var="actionURL" />
<p>哈哈哈hello pluto: </p>
<p>this is actionURL do ${message}</p>
<p>this is actionURL,which i don't know how comes </p>
<p>${actionURL}</p>
<p>the mode is ${mode} }</p>

<form action="${actionURL}">
  what you want do <input type="text" name="message" value="${message}" />
  <input type="submit" value="Submit" />
</form>
</body>
</html>