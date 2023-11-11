package control;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import business.Customer;
import data.CustomerDB;

@WebServlet("/Login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        if (action.equals("login")) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");
//            Customer customerLogin = CustomerDB.customerLogin(email, password);
            if (email.equals("abc@gmail.com") && password.equals("1")) {
                response.sendRedirect("profile.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }

        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
