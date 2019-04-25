<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BidnBuy | Product Bid Details</title>
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
        <div class="container">
            <div class="card" style="width: 18rem;">  
                <img class="card-img-top" 
                    src="${pageContext.request.contextPath}/product-image/<c:out value="${biddedProduct.getImage()}" />">
            </div>
            <c:forEach items="${productbids}" var="productbid">
               <p class="card-title">
                   Fullname: 
                   <c:out value="${productbid.getFirstname()}" /> 
                   <c:out value="${productbid.getLastname()}" />                    
               </p>
               <p>User mail: <c:out value="${productbid.getEmail()}" /></p>
               <p>Bid amount: <c:out value="${productbid.getBidAmount()}" /></p>
               <hr>
            </c:forEach>
        </div>
    </body>
</html>
