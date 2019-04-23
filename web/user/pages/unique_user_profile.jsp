<%-- 
    Document   : unique_user_profile
    Created on : Apr 4, 2019, 7:56:12 AM
    Author     : i-am-prinx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile | Bidx</title>
        <link rel="stylesheet" 
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">
        
        <style>
            .product-list {
                display: flex;
                flex-wrap: wrap;
            }
            
            .product-list div {
                margin: 10px;
            }
            
            .card-body {
                display: flex;
                justify-content: space-between;
            }
            
            .card-body a {
                color: blue;
                font-weight: 600;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div>
                <s:a cssClass="btn btn-sm btn-danger" 
                     action="logout" namespace="/user" > logout </s:a>
                <p>
                    <b>Firstname</b>: 
                    <c:out value="${requested_user.getFirstname()}" />
                </p>
                <p>
                    <b>Lastname</b>: 
                    <c:out value="${requested_user.getLastname()}" />
                </p>
                <p>
                    <b>Email</b> : 
                    <c:out value="${requested_user.getEmail()}" />
                </p>
            </div>
                
            <h4 class="mt-4"> Your products </h4>
            <div class="product-list">
                <c:forEach items="${requested_user_products}" var="product">
                <div class="card" style="width: 18rem;">
                    <img class="card-img-top" 
                         src="${pageContext.request.contextPath}/product-image/${product.getImage()}">
                    <div class="card-body">
                        <h5 class="card-title">
                            <c:out value="${product.getName()}" />
                        </h5>
                        
                        <s:url action="nav-to-product-bid" var="productId" namespace="/product">
                            <s:param name="productId">
                                <c:out value="${product.getId()}" />
                            </s:param>
                        </s:url>
                        
                        <s:a href="%{productId}">Bid</s:a>
                    </div>
                 </div>
                </c:forEach>
            </div>
        </div>                       
    </body>
</html>