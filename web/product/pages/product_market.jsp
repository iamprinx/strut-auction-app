<%-- 
    Document   : product_market
    Created on : Apr 8, 2019, 3:17:12 PM
    Author     : i-am-prinx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="models.Product" %>
<%@page import="java.util.HashSet"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Auction market | Bidx</title>
    </head>
    <body>      
        <h1>All products</h1>
        <% 
            HashSet productlist = (HashSet)session.getAttribute("productlist"); 
        %>
        
        <c:forEach items="${productlist}" var="product">
            <div>
                Name <c:out value="${product.getName()}" /> <br />
                Price: <c:out value="${product.getPrice()}" /> <br />
            </div>
            <br />
        </c:forEach>
    </body>
</html>
