package controller.PaymentAccount;

import business.PaymentAccount;
import business.Customer;
import DAO.PaymentAccountDAO;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/show-defaultPA")
public class ShowDefaultPAServlet extends HttpServlet {

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

        String url = "/profile.jsp";
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String customerId = customer.getCustomerId();
        PaymentAccount defaultPaymentAccount = paymentAccountDAO.findDefaultPaymentAccount(customerId);

       
        if(defaultPaymentAccount != null) {
            request.setAttribute("defaultPaymentAccount", defaultPaymentAccount);
        }

        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }
}
