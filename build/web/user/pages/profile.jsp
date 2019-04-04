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
    </head>
    <body>
        <div class="container-fluid">
            <div>
                <c:set var="user" value="${user}" />
                <s:a cssClass="btn btn-sm btn-danger" 
                     action="logout" namespace="/user" > logout </s:a>
                
                <h4>
                    Hello 
                    <c:out value="${user.getFullname()}" />, 
                    your profile details below
                </h4>
                <p>
                    <b>Firstname</b>: 
                    <c:out value="${user.getFirstname()}" />
                </p>
                <p>
                    <b>Lastname</b>: 
                    <c:out value="${user.getLastname()}" />
                </p>
                <p>
                    <b>Email</b> : 
                    <c:out value="${user.getEmail()}" />
                </p>
                
                <s:a cssClass="btn btn-sm btn-info text-white">edit details</s:a>
                
                <div>
                    <s:a action="nav-to-user-list" namespace="/user" >all users</s:a>
                </div>
                
                <div class="hide">
                    <div class="col-2 mt-1">
                    <s:form action="update" method="post" namespace="/user" >
                        <s:textfield 
                            value="%{#session.user.firstname}" 
                            name="firstname"
                            placeholder="firstname" 
                            cssClass="form-control mb-1" 
                            required="true" />
                        <s:textfield 
                            value="%{#session.user.lastname}"
                            name="lastname" 
                            placeholder="lastname"  
                            cssClass="form-control mb-1" 
                            required="true" />
                        <s:textfield 
                            value="%{#session.user.email}"
                            name="email" 
                            type="email"
                            placeholder="email" 
                            cssClass="form-control mb-1" 
                            required="true" />
                        <s:submit cssClass="btn btn-sm btn-success" value="update" />
                     </s:form>
                    </div>
                </div>
            </div>
        </div>                       
    </body>
</html>
