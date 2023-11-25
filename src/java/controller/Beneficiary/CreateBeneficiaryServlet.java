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

@WebServlet("/create-beneficiary")
public class CreateBeneficiaryServlet extends HttpServlet {

    BeneficiaryDAO beneficiaryDAO = new BeneficiaryDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }

        String url = "/beneficiary.jsp";
        if (action.equals("create")) {
            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("customer");
            String customerId = customer.getCustomerId();
            String accountNumber = request.getParameter("accountNumber");
            String nickName = request.getParameter("nickName");

            try {
                beneficiaryDAO.CreateBeneficiary(accountNumber, nickName, customer);
                request.setAttribute("successMessage", "Your beneciary contact has been added successfully");
            } catch (HandleException e) {
                request.setAttribute("errorMessage", e.getMessage());
            }

            List<Beneficiary> beneficiaries = beneficiaryDAO.findAllBeneficiary(customerId);

            request.setAttribute("Beneficiaries", beneficiaries);

        }

        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
