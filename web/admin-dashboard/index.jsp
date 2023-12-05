<%@page import="business.Employee"%>

<%
    Employee employee = (Employee) session.getAttribute("employee");

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banking</title>
    </head>
    <body>

        <% if (employee != null) { %>
            <jsp:forward page="/customer"></jsp:forward>

        <%  } else { %>

            <jsp:forward page="/Login"></jsp:forward>

        <%  }%> 
    </body>
</html>