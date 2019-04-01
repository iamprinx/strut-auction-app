<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Bidx </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" 
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <div class="cover">
                <div class="wrapper">
                    <div class="head-wrap">
                        <h1>Welcome to Bidx</h1>
                        <p>your product can get the highest bid it deserves</p>
                        <s:a action="nav-to-login" namespace="/user">login</s:a> 
                        <p>
                            Don't have an account ?
                            <s:a action="nav-to-register" namespace="/user">register</s:a> 
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
