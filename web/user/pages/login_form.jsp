<%-- 
    Document   : login_form
    Created on : Mar 24, 2019, 3:14:19 PM
    Author     : i-am-prinx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" 
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">
        <title>login | Bidx</title>
        
        <style>
            .errorMessage {
                color: red;
                font-style: italic;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <div class="cover p-2">
                <s:form action="login" method="post">
                    <div class="col-2">
                        <h4>Login</h4>
                        <s:textfield name="username" placeholder="username" value="%{#session.auth_user.username}" cssClass="form-control mb-1" />
                        <s:password name="password" placeholder="password" value="%{#session.auth_user.password}" cssClass="form-control mb-1" />
                        <s:submit value="login" cssClass="btn btn-info btn-sm mb-2" />
                    </div>
                </s:form>
                <div class="col-2 p-2">
                    <small>
                        need an account ? <s:a action="nav-to-register" namespace="/user">register</s:a> 
                    </small> <br>
                    <small class="text-danger">
                        forgotten password ?
                    </small>
                </div>
            </div>
        </div>
    </body>
</html>
