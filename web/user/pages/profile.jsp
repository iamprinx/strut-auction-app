<%-- 
    Document   : profile
    Created on : Mar 28, 2019, 9:45:54 PM
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
            .welcome-top {
                display:  flex;
                justify-content: space-between;
            }
            
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
        <div class="container-fluid mt-3">
            <div>
                <c:set var="user" value="${auth_user}" />
                
                <div class="welcome-top">
                    <p> 
                        <b><c:out value="${user.getFullname()}" /></b>, 
                        welcome to your profile.
                        <br>
                        your email is <c:out value="${user.getEmail()}" />
                    </p>
                    
                    <s:a cssClass="text-danger" action="logout" namespace="/user" > logout </s:a>
                </div>
                
                
                <div class="profile-core-btns">
                    <button type="button" class="btn btn-info" data-toggle="collapse" 
                            data-target="#edit-profile">edit profile</button>
                
                    <div id="other-buttons">
                        <s:a cssClass="bttn" action="nav-to-user-list" namespace="/user" >all users</s:a> 
                        <br>
                        <s:a cssClass="bttn" action="nav-to-add-product" namespace="/product" >add product</s:a>
                        <br>
                        <s:a cssClass="bttn" action="nav-to-market" namespace="/product" >product market</s:a>
                    </div>
                </div>
            </div>
                
            
            <div id="edit-profile" class="collapse mt-3">
                <div class="col-2 mt-1">
                <s:form action="update" method="post" namespace="/user" >
                    <s:textfield 
                        value="%{#session.auth_user.firstname}" 
                        name="firstname"
                        placeholder="firstname" 
                        cssClass="form-control mb-1" 
                        required="true" />
                    <s:textfield 
                        value="%{#session.auth_user.lastname}"
                        name="lastname" 
                        placeholder="lastname"  
                        cssClass="form-control mb-1" 
                        required="true" />
                    <s:textfield 
                        value="%{#session.auth_user.email}"
                        name="email" 
                        type="email"
                        placeholder="email" 
                        cssClass="form-control mb-1" 
                        required="true" />
                    <s:submit cssClass="btn btn-sm btn-success" value="update" />
                 </s:form>
                </div>
            </div>
        
            <h4 class="mt-4"> Your products </h4>
            <div class="product-list">
                <c:forEach items="${auth_user_products}" var="product">
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
                        
                        <s:url action="bids-details" var="productId" namespace="/product">
                            <s:param name="productId">
                                <c:out value="${product.getId()}" />
                            </s:param>
                        </s:url>                        
                        <s:a href="%{productId}">view</s:a>
                        
                     </div>
                 </div>
                </c:forEach>
            </div>
        </div>  
                
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
 