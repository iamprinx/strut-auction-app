<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Bidx </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="wrapper">
            <div class="cover">
                <div class="container">
                    <div class="head-wrap">
                        <h1>Welcome to Bidx</h1>
                        <p>your product can get the highest bid it deserves</p>
                        <s:a action="navToLogin" namespace="/user">login</s:a> 
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
