<%-- 
    Document   : user_list
    Created on : Apr 4, 2019, 12:56:34 AM
    Author     : i-am-prinx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.HashSet"%>
<%@page import="models.User" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Users | Bidx</title>
    </head>
    <body>
        <% HashSet userlist = (HashSet)session.getAttribute("userlist"); %>
        <h1>All users</h1>
        
        <c:forEach items="${userlist}" var="user">
            <div>
                Fullname: <c:out value="${user.getFullname()}" /> <br />
                Email: <c:out value="${user.email}" /> <br />
                <s:url action="getuser" var="userName">
                    <s:param name="username"><c:out value="${user.username}" /></s:param>
                </s:url>
                <s:a href="%{userName}">view more</s:a>
            </div>
            <br />
        </c:forEach>
        
    </body>
</html>
