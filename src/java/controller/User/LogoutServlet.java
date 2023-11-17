package controller.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        String url = "/index.jsp";
        if (action.equals("logout")) {

            HttpSession session = request.getSession();
            session.removeAttribute("email");
            session.removeAttribute("customerName");
            session.removeAttribute("citizenId");
            session.invalidate();

            url = "/login.jsp";
        }

        servletContext.getRequestDispatcher(url)
                .forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
