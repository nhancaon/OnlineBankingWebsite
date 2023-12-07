<%@page import="business.Customer"%>

<%
    Customer customer = (Customer) session.getAttribute("customer");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banking</title>
    </head>
    <body>

        <% if (customer != null) { %>
            <jsp:forward page="/Profile"></jsp:forward>

        <%  } else { %>

            <jsp:forward page="/Login"></jsp:forward>
            
        <%  }%> 
    </body>
</html>