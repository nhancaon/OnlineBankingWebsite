
<%

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

    if (session.getAttribute("email") == null) {
        response.sendRedirect("login.jsp");
    }
%>