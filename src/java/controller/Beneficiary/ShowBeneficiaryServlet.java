package controller.Beneficiary;

import business.Customer;
import DAO.BeneficiaryDAO;
import Exception.HandleException;
import business.Beneficiary;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Beneficiary")
public class ShowBeneficiaryServlet extends HttpServlet {

    BeneficiaryDAO beneficiaryDAO = new BeneficiaryDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ServletContext servletContext = getServletContext();

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        String url = "/beneficiary.jsp";
        
        if (action.equals("findContacts")) {
            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("customer");
            String customerId = customer.getCustomerId();
            String parameter = request.getParameter("parameter");
            System.out.print(parameter);
            try {

                List<Beneficiary> beneficiaries = beneficiaryDAO.findAllBeneficiaryByNameOrAccountNumber(parameter, customerId);

                request.setAttribute("Beneficiaries", beneficiaries);
            } catch (HandleException e) {
                request.setAttribute("errorMessage", e.getMessage());
            }

        }

        servletContext.getRequestDispatcher(url)
                .forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String url = "/beneficiary.jsp";

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String customerId = customer.getCustomerId();
        List<Beneficiary> beneficiaries = beneficiaryDAO.findAllBeneficiaryByCustomerId(customerId);

        request.setAttribute("Beneficiaries", beneficiaries);
        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }

}
