package controller.Beneficiary;

import business.Customer;
import DAO.BeneficiaryDAO;
import business.Beneficiary;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/show-beneficiary")
public class ShowBeneficiaryServlet extends HttpServlet {

    BeneficiaryDAO beneficiaryDAO = new BeneficiaryDAO();

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

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String customerId = customer.getCustomerId();
        List<Beneficiary> beneficiaries = beneficiaryDAO.findAllBeneficiary(customerId);

        request.setAttribute("Beneficiaries", beneficiaries);
        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }

}
