<%-- 
    Document   : registration_form
    Created on : Mar 24, 2019, 3:47:27 PM
    Author     : i-am-prinx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>register | Bidx</title>
        <link rel="stylesheet" 
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">        
        <style>
            .errorMessage {
                color: red;
                font-style: italic;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
            <div class="cover">
                <div class="container">
                    <div class="head-wrap">  
                        <div class="cover p-2">
                        <s:form action="register" method="post">
                            <div class="col-3">
                                <h4>Register</h4>
                                <p>All details are required</p>
                                <s:textfield 
                                    name="username" placeholder="username" 
                                    cssClass="form-control mb-1" 
                                />
                                
                                <s:textfield 
                                    name="firstname" placeholder="firstname" 
                                    cssClass="form-control mb-1" 
                                />
                                
                                <s:textfield 
                                    name="lastname" placeholder="lastname" 
                                    cssClass="form-control mb-1" 
                                />
                                
                                <s:textfield 
                                    name="email" placeholder="email" 
                                    cssClass="form-control mb-1" 
                                />
                                
                                <s:password 
                                    name="password" placeholder="password" 
                                    cssClass="form-control mb-1" 
                                />
                                
                                <s:password 
                                    name="confirm_password" placeholder="confirm passworod" 
                                    cssClass="form-control mb-1" 
                                />
                                
                                <s:submit value="register" cssClass="btn btn-success btn-sm mb-2" />
                            </div>
                        </s:form>
                        <p>
                            have an account ? <s:a action="nav-to-login" namespace="/user">login</s:a> 
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
