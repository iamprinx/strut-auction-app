<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product upload | Bidx</title>
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
                        <s:form action="addProduct" method="post" namespace="/product" enctype="multipart/form-data">
                            <div class="col-3">
                                <h4>Add Product</h4>
                                <p>All details are required</p>
                                <s:textfield 
                                    name="name" placeholder="name" 
                                    cssClass="form-control mb-1" 
                                />
                                
                                <s:textfield type="number" 
                                    name="price" placeholder="price" 
                                    cssClass="form-control mb-1" 
                                />
                                
                                <s:file name="image" />
                                
                                <s:submit value="upload" />
                            </div>
                        </s:form>
                    </div>
                </div>
            </div>
    </body>
</html>
