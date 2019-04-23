

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bid</title>
        <link rel="stylesheet" 
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <img class="img-fluid" 
                 src="${pageContext.request.contextPath}/product-image/${bid_product.getImage()}">
            <h3>${bid_product.getName()}</h3>
                
            <h5>Bid on this product</h5>
            <s:form>
                <div class="form-group">
                    <s:textfield 
                        cssClass="form-control" 
                        type="number" 
                        name="amount" 
                        placeholder="Amount"
                        required="true" />
                </div>
                <s:submit cssClass="btn btn-sm btn-danger" value="Bid" />
            </s:form>
        </div>
    </body>
</html>
