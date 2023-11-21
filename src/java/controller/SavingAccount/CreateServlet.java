package controller.SavingAccount;

import business.SavingAccount;
import business.Customer;
import DAO.SavingAccountDAO;
import Exception.CreateException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/create-saving-account")
public class CreateServlet extends HttpServlet {

    SavingAccountDAO savingAccountDAO = new SavingAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String customerId = customer.getCustomerId();
        SavingAccount savingAcc=null;
        savingAcc=savingAccountDAO.findSavingAccountByCusId(customerId);

        if (action == null) {
            action = "join";  
        }
        String url = "/accountSaving.jsp";
        if (action.equals("create")) {
            
            String accountNumber = request.getParameter("acNumber");
            String pinNumber = request.getParameter("pinNumber");
            String amount = request.getParameter("savingAmount");
            try {
                savingAccountDAO.CreateSavingAccount(customer, accountNumber, pinNumber,Integer.parseInt(amount));
                request.setAttribute("successMessage", "Your saving account has been created successfully");
            } catch (CreateException e) {

                request.setAttribute("errorMessage", e.getMessage());
            }        
        }
        if(savingAcc!=null){
            request.setAttribute("savingAccount", savingAcc);
            
        }
        System.out.println(savingAcc);
        servletContext.getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
