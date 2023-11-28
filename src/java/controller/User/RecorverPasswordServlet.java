package controller.User;

import DAO.CustomerDAO;
import Exception.HandleException;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "RecorverPasswordServlet", urlPatterns = {"/recover-password"})
public class RecorverPasswordServlet extends HttpServlet {

    CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String url = "/login.jsp";
        String recoverEmail = request.getParameter("recoverEmail");
        System.out.println(recoverEmail);

        try {
            customerDAO.RecoverPassword(recoverEmail);
            request.setAttribute("successMessage", "Your new password has been sent to your email, Please check!");
        } catch (HandleException e) {
            request.setAttribute("errorMessage", e.getMessage());
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
