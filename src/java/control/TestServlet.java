package control;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import business.*;
import data.*;
public class TestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/index.html";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.jsp";    // the "join" page
        } else if (action.equals("add")) {
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String emailU = request.getParameter("email");

            // store data in User object
            userhaha user = new userhaha();
            user.setEmail(emailU);
            user.setFirstName(firstName);
            user.setLastName(lastName);

            // validate the parameters
            String message;
            if (1==2) {
                message = "This email address already exists.<br>"
                        + "Please enter another email address.";
                url = "/index.jsp";
            } else {
               
                message = "";
                url = "/thanks.jsp";
                System.out.println(emailU);
                UserhahaDB.insert(user);

            }
            request.setAttribute("user", user);
            request.setAttribute("message", message);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
