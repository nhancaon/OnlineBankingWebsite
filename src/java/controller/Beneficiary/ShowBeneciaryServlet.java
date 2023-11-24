
package controller.Beneficiary;

import business.PaymentAccount;
import business.Customer;
import DAO.PaymentAccountDAO;
import Exception.HandleException;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;


@WebServlet(name = "ShowBeneciaryServlet", urlPatterns = {"/ShowBeneciary"})
public class ShowBeneciaryServlet extends HttpServlet {

    PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String url = "/beneficiary.jsp";
       

        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }
}
