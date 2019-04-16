<%-- 
    Document   : product_market
    Created on : Apr 8, 2019, 3:17:12 PM
    Author     : i-am-prinx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Auction market | Bidx</title>
        <link rel="stylesheet" 
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">
    </head>
    <body>      
        <div class="container-fluid">
            <h1>All products</h1>
            <c:forEach items="${products}" var="product">
                <p>
                    <b>Name:</b> 
                    <c:out value="${product.getName()}" />
                </p>
                <p>
                    Price: <c:out value="${product.getPrice()}" />
                </p>
                <img src="${pageContext.request.contextPath}/product-image/${product.getImage()}">
            </c:forEach>
        </div>
    </body>
</html>
