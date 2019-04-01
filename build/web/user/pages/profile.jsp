<%-- 
    Document   : profile
    Created on : Mar 28, 2019, 9:45:54 PM
    Author     : i-am-prinx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <h4>Hello <s:property value="getFullname()" />, your profile details below</h4>
                <p><b>Firstname</b>: <s:property value="getFirstname()" /></p>
                <p><b>Lastname</b>: <s:property value="getLastname()" /></p>
                <p><b>Email</b> : <s:property value="getEmail()" /></p>
                <s:a cssClass="btn btn-sm btn-info text-white">edit details</s:a>
            </div>
        </div>                       
    </body>
</html>
