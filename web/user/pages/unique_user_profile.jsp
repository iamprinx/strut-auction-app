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
        </div>                       
    </body>
</html>